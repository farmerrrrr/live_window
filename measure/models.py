from django.db import models

class Measurer(models.Model):
    serialNumber = models.CharField("Device", max_length=128, null=False, on_delete=models.CASCADE, primary_key=True, db_column="serialNumber")
    amount = models.FloatField(null=True)
    timestamp = models.DateTimeField(null=True)