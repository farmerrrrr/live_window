from rest_framework import serializers
from .models import Measurer, Device, User

class MeasurerSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Measurer
        fields = '__all__'
        