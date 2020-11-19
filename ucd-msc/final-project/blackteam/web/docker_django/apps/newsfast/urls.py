from django.conf.urls import url, include
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from rest_framework import routers

from . import views

router = routers.DefaultRouter()
router.register(r'jobs', views.JobViewSet)

urlpatterns = [
    url(r'^$', views.home, name='home'),
    url(r'^login/$', views.login, name='login'),
    url(r'^followers/$', views.followers, name='followers'),
    url(r'^topics/$', views.topics, name='topics'),
    url(r'^LAR', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]
urlpatterns += staticfiles_urlpatterns()
