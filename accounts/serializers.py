from rest_framework import serializers
from .models import User
from .models import Device

class UserSerializer(serializers.ModelSerializer) :
    class Meta :
        model = User
        fields = '__all__'
        
class generalSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Device
        fields = ['userID', 'serialNumber']