from docker_django.apps.newsfast.models import UserProfile
from django.core.exceptions import ObjectDoesNotExist
import logging
# Get an instance of a logger
logger = logging.getLogger(__name__)

def add_user_to_db(**kwargs):
    try:
        UserProfile.objects.get(twitter_id=kwargs.get('twitter_id')) # raises Entry.DoesNotExist
        logger.debug('Not inserting twitter id, object already exists')
    except ObjectDoesNotExist:
        # OK to insert twitter id
        user = UserProfile.objects.create()
        user.twitter_id = kwargs.get('twitter_id','')
        user.twitter_screen_name = kwargs.get('twitter_screen_name','')
        user.twitter_name = kwargs.get('twitter_name','')
        user.email_address = kwargs.get('email_address','')
        user.linkedin_id = kwargs.get('linkedin_id','')
        user.facebook_id = kwargs.get('facebook_id','')
        user.save()
