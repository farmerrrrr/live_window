from django.db import models

# Create your models here.
class Measurer(models.Model):
    serialNumber = models.CharField(max_length=128, null=False)
    amount = models.FloatField(null=True)
    timestamp = models.DateTimeField(null=False)