# Authorization Providers 

from authomatic.providers import oauth2, oauth1

CONFIG = {

    'tw': { # Your internal provider name

        # Provider class
        'class_': oauth1.Twitter,

        # Twitter is an AuthorizationProvider so we need to set several other properties too:
        # Registered by LOS
        'consumer_key': 'HA94552kU7AuvKPoweFOiiVFN',
        'consumer_secret': 'jkFigA7o1VeLw8v4WGAjciAmr5k65teDjqhnL6wjv7drUMx3ZY',
    },

    'fb': {

        'class_': oauth2.Facebook,

        # Facebook is an AuthorizationProvider too.
        'consumer_key': '########################',
        'consumer_secret': '########################',

        # But it is also an OAuth 2.0 provider and it needs scope.
        'scope': ['user_about_me', 'email', 'publish_stream'],
    }

}
