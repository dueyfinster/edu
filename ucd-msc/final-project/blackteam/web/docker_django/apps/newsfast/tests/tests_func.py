import os
# from django.test import LiveServerTestCase
from django.utils.unittest import SkipTest
from selenium.webdriver.support.ui import WebDriverWait
from django.contrib.staticfiles.testing import StaticLiveServerTestCase
from selenium.webdriver.firefox.webdriver import WebDriver
# from selenium import webdriver


class FunctionalTest(StaticLiveServerTestCase):
    @classmethod
    def setUpClass(cls):
        cls.selenium = WebDriver()
        if os.environ.get('DJANGO_SELENIUM_TESTS', False):
            raise SkipTest('Selenium tests not requested')
        super(FunctionalTest, cls).setUpClass()

    @classmethod
    def tearDownClass(cls):
        cls.selenium.quit()
        super(FunctionalTest, cls).tearDownClass()

    def test_index(self):
        self.selenium.get(self.live_server_url)
        self.selenium.get_screenshot_as_file('/tmp/website.png')
        wait = WebDriverWait(self.selenium, 10)
        print("TITLE:", self.selenium.title)
        wait.until(lambda selenium: self.selenium.title.lower().startswith('n'))
        self.assertIn("News Fast", self.selenium.title)

#   def test_ten_news_items_on_page(self):
#       rows = self.selenium.find_elements_by_xpath(
#           "//div[contains(@class, 'row')]")
#       self.assertTrue(len(rows) >= 10)

    def test_not_index(self):
        self.selenium.get(self.live_server_url)
        assert "Foobar" not in self.selenium.title
