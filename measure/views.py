from django.shortcuts import render

# Create your views here.
from django.views import View
from django.http import HttpResponse, JsonResponse

class MeasureView(View):
    def get(self, request):
        dummy_data = {
            'name': '죠르디',
            'type': '공룡',
            'job': '편의점알바생',
            'age': 5
        }
        return JsonResponse(dummy_data)

    def post(self, request):
        return HttpResponse("Post 요청을 잘받았다")

    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")