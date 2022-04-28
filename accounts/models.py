from django.db import models

class User(models.Model):
    userID = models.CharField(max_length=128, null=False, primary_key=True)
    password = models.CharField(max_length=128, null=False)
    
class Device(models.Model):
    serialNumber = models.CharField(max_length=128, null=False, primary_key=True)
    userID = models.ForeignKey("User", max_length=128, null=True, on_delete=models.SET_NULL, db_column="userID")
    use = models.CharField(max_length=10, null=False)
