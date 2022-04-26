from django.db import models

# Create your models here.
class Controller(models.Model):
    serialNumber = models.CharField(max_length=128, null=False)
    status = models.BooleanField(null=False)
    timestamp = models.DateTimeField(null=False)
    
class Routine(models.Model):
    routineNumber = models.CharField(max_length=128, null=False)
    userID = models.CharField(max_length=128, null=False)
 #  dayOfTheWeek = models.CharField(max_length=256, null=False)
    startTime = models.TimeField(null=False)
    endTime = models.TimeField(null=False)
    