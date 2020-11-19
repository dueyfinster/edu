README
=======

To serve JSON File to app:

$ cd neilgrogan-13204052-android-project/ripapp/app/src/main/assets/

# For Python 2
$ python -m SimpleHTTPServer

# For Python 3
$ python -m http.server 


Get your IP address (of machine running python server) and modify the main activity to have correct URL:

$ cd neilgrogan-13204052-android-project/ripapp/app/src/main/java/ie/ucd/cs/neilgrogan13204052/deathnotices/

$ vim DeathNotices.java

and modify SERVER_URL constant on line 20 to match IP of python server.