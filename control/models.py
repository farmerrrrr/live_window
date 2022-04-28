from django.db import models

# 완료
class Controller(models.Model):
    serialNumber = models.CharField("Device", max_length=128, null=False, on_delete=models.CASCADE, primary_key=True, db_column="serialNumber")
    status = models.BooleanField(null=True)
    timestamp = models.DateTimeField(null=True)
    
class Routine(models.Model):
    routineNumber = models.AutoField(primary_key=True)
    userID = models.CharField("User", max_length=128, on_delete=models.CASCADE, null=False, db_column="userID")
 #  dayOfTheWeek = models.CharField(max_length=256, null=False)
    startTime = models.TimeField(null=False)
    endTime = models.TimeField(null=False)
    