from django.test import TestCase
from docker_django.apps.newsfast.models import RSSFeed
import datetime

# Create your tests here.
class RSSFeedTestCase(TestCase):
    def setUp(self):
        RSSFeed.objects.create(name="Test RSS Feed", url="http://www.example.com",
                            twitter_username="sample")


    def test_rss_feed_values_set_correctly(self):
        item = RSSFeed.objects.get(name="Test RSS Feed")
        self.assertEqual(item.url, "http://www.example.com")
        self.assertEqual(item.twitter_username, "sample")
