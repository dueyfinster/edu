# See: https://docs.djangoproject.com/en/1.8/howto/custom-management-commands/
from django.core.management.base import BaseCommand, CommandError
from django.contrib.auth.models import User
from django.test.client import Client

# store the password to login later
password = 'admin'

class Command(BaseCommand):
    help = 'Creates an admin user'

    def handle(self, *args, **options):
      username='admin'
      try:
          self.stdout.write('Checking if admin user exists')
          User.objects.get(username__iexact=username)
          self.stdout.write('Using existing Admin User')
      except User.DoesNotExist:
          self.stdout.write('Going to create admin user...')
          User.objects.create_superuser(username, 'myemail@test.com', password)
          self.stdout.write('Created admin user')
      self.stdout.write('Admin user has been processed')
