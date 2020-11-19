# See: https://docs.djangoproject.com/en/1.8/howto/custom-management-commands/
from django.core.management.base import BaseCommand
from docker_django.apps.newsfast.models import RSSFeed
from docker_django.apps.newsfast.models import Topic
import os
import feedparser
import csv


class Command(BaseCommand):
    help = 'Imports CSV files in to the database'

    def handle(self, *args, **options):
        self.import_feeds()
        self.import_topics()

    def import_topics(self):
        self.stdout.write('Would import topics here')
        if len(Topic.objects.all()) is 0:
            script_path = os.path.dirname(os.path.realpath(__file__))
            filepath = os.path.join(script_path, '../../data/topic_list.csv')
            with open(filepath, newline='') as csvfile:
                topic_list = csv.reader(csvfile, delimiter=',')
                next(topic_list)
                for row in topic_list:
                    topic = Topic()
                    topic.name = row[0]
                    topic.save()
            self.stdout.write('Successfully imported topics')
        else:
            self.stdout.write('Topics already imported, please delete '
                              'existing to reimport')

    def import_feeds(self):
        self.stdout.write('Going to try import feeds...')
        if len(RSSFeed.objects.all()) is 0:
            script_path = os.path.dirname(os.path.realpath(__file__))
            filepath = os.path.join(script_path, '../../data/feed_list.csv')
            with open(filepath, newline='') as csvfile:
                feed_list = csv.reader(csvfile, delimiter=',')
                next(feed_list)
                for row in feed_list:
                    feed = RSSFeed()
                    d = feedparser.parse(row[0])
                    feed.name = d.feed.title
                    feed.url = row[0]
                    feed.twitter_username = row[1]
                    feed.save()
            self.stdout.write('Successfully imported feeds')
        else:
            self.stdout.write('Feeds already imported, please delete '
                              'existing to reimport')

