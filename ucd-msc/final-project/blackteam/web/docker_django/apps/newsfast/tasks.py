from functools import wraps

from docker_django.celery import app
from .lock import require_lock
from .models import RSSFeed, Article, UserArticle, Job
import feedparser as fd
import logging
# Get an instance of a logger
logger = logging.getLogger(__name__)


def update_job(fn):
    @wraps(fn)
    def wrapper(job_id, *args, **kwargs):
        job = Job.objects.get(id=job_id)
        job.status = 'started'
        job.save()
        try:
            result = fn(*args, **kwargs)
            job.result = result
            job.status = 'finished'
            job.save()
        except:
            job.result = None
            job.status = 'failed'
            job.save()

    return wrapper


@app.task
def process_rss_feeds():
    rss_feeds = RSSFeed.objects.all()

    for rss_feed in rss_feeds:
        feeds = fd.parse(rss_feed.url)
        for entry in feeds.entries:
            try:
                item = Article()
                item.title = entry.title
                item.summary = entry.summary
                item.url = entry.link

                if hasattr(entry, 'published'):
                    item.date_published = entry.published
                item.rss_feed_name = rss_feed.name
                item.twitter_username = rss_feed.twitter_username

                if Article.objects.filter(url=item.url).count() < 1:
                    item.save()

                    require_lock(UserArticle, 'EXCLUSIVE')
                    new_item = UserArticle()
                    new_item.user_id = '12345'  # default user
                    new_item.article = item

                    # default settings for rank score
                    if len(item.summary) > 1000:
                        new_item.rank_score = 50
                    elif '<img' in item.summary:
                        new_item.rank_score = 70
                    else:
                        new_item.rank_score = 0

                    new_item.date = item.date_published
                    new_item.save()
            except:
                logging.exception('Got exception on process_rss_feeds')

    return True


@app.task
@update_job
def power(n):
    """Return 2 to the n'th power"""
    return 2 ** n


@app.task
@update_job
def fib(n):
    """Return the n'th Fibonacci number.
    """
    if n < 0:
        raise ValueError("Fibonacci numbers are only defined for n >= 0.")
    return _fib(n)


def _fib(n):
    if n == 0 or n == 1:
        return n
    else:
        return _fib(n - 1) + _fib(n - 2)


TASK_MAPPING = {
    'power': power,
    'fibonacci': fib,
    'process_rss_feeds': process_rss_feeds
}
