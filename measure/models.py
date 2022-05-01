from django.db import models
from accounts.models import Device

class Measurer(models.Model):
    serialNumber    = models.ForeignKey("accounts.Device", max_length=128, null=False, on_delete=models.CASCADE, primary_key=True, db_column="serialNumber", unique=True, related_name="serialNumber")
    amount          = models.FloatField(null=True)
    timestamp       = models.DateTimeField(null=True)