import json
import re

from django.shortcuts import render
from django.views import View
from django.http import HttpResponse, JsonResponse
from grpc import ServerInterceptor

from django.db.models import Q
from measure.models import Measurer
from accounts.models import User, Device
from .serializers import generalSerializer

class MeasureView(View):
    # getFDAmount
    def get(self, request):
        data = json.loads(request.body)
        userID = data.get('userID', None)
        serialNumber = data.get('serialNumber', None)

        if not(userID and serialNumber):
            return JsonResponse({'error': '전달된 parameter가 부족합니다.'}, status=400)

        if not(User.objects.filter(Q(userID=userID)).exists()):
            return JsonResponse({'error': '유효하지 않은 사용자입니다.'}, status=400)
        
        if not(Device.objects.filter(Q(userID=userID) | Q(serialNumber=serialNumber)).exists()):
            return JsonResponse({'error': '디바이스 정보가 없습니다.'}, status=400)
        
        if not(Measurer.objects.filter(Q(serialNumber=serialNumber)).exists()):
            return JsonResponse({'error': '미세먼지 농도 조회에 실패하였습니다.'}, status=400)

        query = Measurer.objects.get(serialNumber=serialNumber)        

        finedustAmount = {
            'amount': query.amount,
            'timestamp': query.timestamp
        }
        
        return JsonResponse({'response': finedustAmount}, status=200)


    def post(self, request):
        return HttpResponse("Post 요청을 잘받았다")

    # (arduino)updateFDAmount
    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")