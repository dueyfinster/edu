from django.contrib import admin
from .models import RSSFeed
from .models import UserProfile
from .models import Article
from .models import UserArticle
from .models import Topic


class RSSFeedAdmin(admin.ModelAdmin):
        list_display = ['name', 'twitter_username', 'url']
        search_fields = ['name', 'twitter_username', 'url']


class TopicAdmin(admin.ModelAdmin):
        list_display = ['name']
        search_fields = ['name']


class UserAdmin(admin.ModelAdmin):
        list_display = ['twitter_id', 'twitter_screen_name', 'twitter_name', 'email_address', 'facebook_id', 'linkedin_id']
        search_fields = ['twitter_id', 'twitter_screen_name', 'twitter_name', 'email_address', 'facebook_id', 'linkedin_id']


class ArticleAdmin(admin.ModelAdmin):
        list_display = ['id', 'title', 'url', 'summary', 'rss_feed_name']
        search_fields = ['title', 'url', 'summary', 'rss_feed_name']


class UserArticleAdmin(admin.ModelAdmin):
        model = UserArticle
        list_display = ['user_id', 'get_article', 'rank_score', 'date']
        search_fields = ['user_id', 'get_article', 'rank_score', 'date']

        def get_article(self, obj):
            return obj.article.title
        get_article.admin_order_field  = 'id'  #Allows column order sorting

# Register your models here.
admin.site.register(RSSFeed, RSSFeedAdmin)
admin.site.register(Topic, TopicAdmin)
admin.site.register(UserProfile, UserAdmin)
admin.site.register(Article, ArticleAdmin)
admin.site.register(UserArticle, UserArticleAdmin)
