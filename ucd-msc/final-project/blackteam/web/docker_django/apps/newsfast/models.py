from django.db import models


class RSSFeed(models.Model):
    name = models.TextField(blank=False, null=True)
    url = models.TextField(blank=False, null=True)
    twitter_username = models.TextField(max_length=15, blank=True, null=True)
    date_added = models.DateField(auto_now=True, null=True)
    last_updated = models.DateField(auto_now=True, null=True)

    def __str__(self):
        return self.name


class Topic(models.Model):
    name = models.TextField(blank=False, null=True)

    def __str__(self):
        return self.name


class Article(models.Model):
    title = models.TextField(blank=False, null=True)
    url = models.TextField(blank=False, null=True)
    summary = models.TextField(blank=True, null=True)
    date_published = models.DateTimeField(auto_now_add=True, blank=True)
    rss_feed_name = models.TextField(blank=True, null=True)
    twitter_username = models.TextField(max_length=15, blank=True, null=True)

    def __str__(self):
        return self.id


class UserArticle(models.Model):
    user_id = models.TextField(max_length=15, blank=False, null=False, db_index=True)
    article = models.ForeignKey('Article')
    rank_score = models.DecimalField(max_digits=4, decimal_places=2)
    date = models.DateTimeField(auto_now_add=True, blank=True)

    def __str__(self):
        return self.id


class UserProfile(models.Model):
    twitter_screen_name = models.TextField(max_length=15, blank=False, null=True, unique=True)
    twitter_id = models.TextField(max_length=15, blank=False, null=True, unique=True)
    twitter_name = models.TextField(max_length=15, blank=True, null=True)
    email_address = models.TextField(max_length=15, blank=True, null=True)
    facebook_id = models.TextField(max_length=15, blank=True, null=True)
    linkedin_id = models.TextField(max_length=15, blank=True, null=True)
    twitter_profile_checked_dt = models.DateField(auto_now=True, null=True)

    def __str__(self):
        return self.twitter_id


class UserPreferences(models.Model):
    user_profile = models.ForeignKey(UserProfile)
    user_pref_id = models.CharField(max_length=50)
    item_tag = models.CharField(max_length=50)
    last_clicked_dte = models.DateTimeField('last clicked date')
    like_dislike_ind = models.CharField(max_length=1, default='L')


# kk may not be needed for deployment. entity names were not displaying correctly in my unit testing so had to add these.
#    class Meta:
#        verbose_name = 'UserPreferences'
#        verbose_name_plural = 'UserPreferences'


class Job(models.Model):
    TYPES = (
        ('fibonacci', 'fibonacci'),
        ('power', 'power'),
        ('process_rss_feeds', 'process_rss_feeds'),
    )

    STATUSES = (
        ('pending', 'pending'),
        ('started', 'started'),
        ('finished', 'finished'),
        ('failed', 'failed'),
    )

    type = models.CharField(choices=TYPES, max_length=20)
    status = models.CharField(choices=STATUSES, max_length=20)

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    argument = models.PositiveIntegerField()
    result = models.IntegerField(null=True)

    def save(self, *args, **kwargs):
        super(Job, self).save(*args, **kwargs)
        if self.status == 'pending':
            from .tasks import TASK_MAPPING

            task = TASK_MAPPING[self.type]
            task.delay(job_id=self.id, n=self.argument)
