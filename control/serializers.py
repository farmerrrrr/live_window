from rest_framework import serializers
from .models import Controller
from .models import Routine

class ControllerSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Controller
        fields = '__all__'
        
class RoutineSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Routine
        fields = '__all__'