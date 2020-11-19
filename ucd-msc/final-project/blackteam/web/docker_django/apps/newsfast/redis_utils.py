from redis import Redis
import logging
from docker_django.apps.newsfast.nlp_utils import NLP_extract_keywords
# Get an instance of a logger
logger = logging.getLogger(__name__)

def extract_store_keywords(feed_items):
    redis = Redis(host='redis', port=6379)
    for i in range(1):
        my_news_item = feed_items[i]
        my_nlp = NLP_extract_keywords(my_news_item.title)
        my_keywords = my_nlp.extract_keywords()        
        for my_keyword in my_keywords:
            redis.sadd(my_news_item.title,my_keyword)
    return True





