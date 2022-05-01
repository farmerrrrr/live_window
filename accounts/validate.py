# Define functions that perform validation.

from django.contrib.auth.models import User
from .serializers import DeviceSerializer, UserSerializer

def verifyPW(password) :
    return True

def validateDevice(serialNumber, use) :
    serializer = DeviceSerializer(bbs)
    return True