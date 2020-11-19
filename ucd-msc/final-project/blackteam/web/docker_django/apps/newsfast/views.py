from django.shortcuts import render, redirect
from django.http import HttpResponseRedirect, HttpResponse
# from .models import Item
from redis import Redis
import feedparser as fd
import os
from django.conf import settings
import tweepy
from authomatic import Authomatic
from authomatic.adapters import DjangoAdapter
from docker_django.apps.newsfast.webapp_authproviders import CONFIG
from docker_django.apps.newsfast.db_utils import add_user_to_db
from rest_framework import mixins, viewsets
from .models import Article, UserArticle, Job, Topic
from .serializers import JobSerializer
from .lock import require_lock
from docker_django.apps.newsfast.redis_utils import *

redis = Redis(host='redis', port=6379)
authomatic = Authomatic(CONFIG, 'secret random string - LOS - dont know what this is for')

import logging
# Get an instance of a logger
logger = logging.getLogger(__name__)


def home(request):
    counter = redis.incr('counter')
    version = os.environ['NEWSFAST_VERSION']

    client_id = '12345'  # default
    if request.session and 'user_id' in request.session and request.session['user_id']:
        client_id = request.session['user_id']
    ids = UserArticle.objects.values_list('article_id', flat=True).filter(user_id=client_id).order_by('date', '-rank_score')[:10]
    feeds = Article.objects.filter(id__in = set(ids))

    twitter_screen_name = ''
    # include twitter screen name if user logged in
    if request.session and 'twitter_screen_name' in request.session and request.session['twitter_screen_name']:
        twitter_screen_name = request.session['twitter_screen_name']
    return render(request, 'home.html',
                  {'feeds': feeds, 'counter': counter, 'version': version, 'twitter_screen_name': twitter_screen_name})


def login(request):
    provider_name = 'tw'
    # We we need the response object for the adapter.
    response = HttpResponse()

    # Start the login procedure - this uses the authomatic library which can also be used for facebook etc
    # for twtter this will redirect user to twitter to authenticate app
    # login will then get called a 2nd time as the callback
    # TODO handle user rejecting the app when sent to twtter sign in
    result = authomatic.login(DjangoAdapter(request, response), provider_name)

    # If there is no result, the login procedure is still pending.
    # Don't write anything to the response if there is no result!
    if result:
        # If there is result, the login procedure is over and we can write to response.
        response.write('<a href="..">Home</a>')

        if result.error:
            # Login procedure finished with an error.
            response.write('<h2>Damn that error: {0}</h2>'.format(result.error.message))

        elif result.user:
            # Hooray, we have the user!

            # OAuth 2.0 and OAuth 1.0a provide only limited user data on login,
            # We need to update the user to get more info.
            if not (result.user.name and result.user.id):
                result.user.update()

            # If there are credentials (only by AuthorizationProvider),
            # we can _access user's protected resources.
            if result.user.credentials:

                # Each provider has it's specific API.
                if result.provider.name == 'tw':
                    # Insert twitter info to DB
                    kwargs = {'twitter_id': result.user.id, 'twitter_screen_name': result.user.name}
                    add_user_to_db(**kwargs)

                    # Keep the access token and secret for the session - we will use this to setup tweepy
                    response.write('Your are logged in with Twitter.<br />')
                    response.write(result.user.credentials)
                    response.write(result.user.credentials.consumer_secret)

                    # Keep the access token and secret - we will use this to setup tweepy
                    # See followers example for how to setup tweepy
                    request.session['twitter_access_token_key'] = result.user.credentials.token
                    request.session['twitter_access_token_secret'] = result.user.credentials.token_secret
                    request.session['twitter_screen_name'] = result.user.name
                    return redirect('home')

    return response


def followers(request):
    response = HttpResponse()

    auth = tweepy.OAuthHandler(CONFIG['tw']['consumer_key'], CONFIG['tw']['consumer_secret'])
    auth.set_access_token(request.session['twitter_access_token_key'], request.session['twitter_access_token_secret'])
    # So now that we have our OAuthHandler equipped with an access token, we are ready for business:
    api = tweepy.API(auth)

    # Show all followes for the authenticated user
    for follower in api.followers():
        text = follower
        response.write(u'<h3>{0}</h3>'.format(text))
        response.write(u'Follower:')
    return response


def topics(request):
    response = HttpResponse()
    topics = Topic.objects.all()
    # Show all followes for the authenticated user
    for topic in topics:
        response.write(u'<h3>{0}</h3>'.format(topic))
    return response


class JobViewSet(mixins.CreateModelMixin,
                 mixins.ListModelMixin,
                 mixins.RetrieveModelMixin,
                 viewsets.GenericViewSet):
    """
    API endpoint that allows jobs to be viewed or created.
    """
    queryset = Job.objects.all()
    serializer_class = JobSerializer
