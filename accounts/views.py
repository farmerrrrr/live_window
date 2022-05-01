#from django.shortcuts import render
#from django.contrib import auth
#from django.contrib.auth import authenticate

from django.contrib.auth.models import User
# from django.views import View
from django.http import HttpResponse, JsonResponse
from .serializers import DeviceSerializer, UserSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.http import Http404

class UserView(APIView):
    # getUser
    def get(self, request):
        dummy_data = {
            'name': '죠르디',
            'type': '공룡',
            'job': '편의점알바생',
            'age': 5
        }
        return JsonResponse(dummy_data)

    # registerUser
    def post(self, request):

        return HttpResponse("Post 요청을 잘받았다")

    # changePW
    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")

    # deleteUser
    def delete(self, request):
        return HttpResponse("Delete 요청을 잘받았다")
    
class DeviceView(APIView):
    # getDevice
    def get(self, request):
        dummy_data = {
            'name': '죠르디',
            'type': '공룡',
            'job': '편의점알바생',
            'age': 5
        }
        return JsonResponse(dummy_data)

    # setDevice
    def post(self, request):
        serializer = DeviceSerializer(data=request.data)
        if serializer.is_valid(): 
            serializer.save() 
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    # changeDevice
    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")

    # deleteDevice
    def delete(self, request):
        return HttpResponse("Delete 요청을 잘받았다")