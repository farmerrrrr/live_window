from django.db import models

# Create your models here.
class User(models.Model):
    userID = models.CharField(max_length=128, null=False)
    password = models.CharField(max_length=128, null=False)
    phoneNumber = models.CharField(max_length=256, null=False) # models.PhoneNumberField(_(""))?
    
class Device(models.Model):
    serialNumber = models.CharField(max_length=128, null=False)
    userID = models.CharField(max_length=128, null=True)
    use = models.CharField(max_length=256, null=False)