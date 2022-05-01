from django.views import View
from django.http import HttpResponse, JsonResponse
from .serializers import RoutineSerializer, ControllerSerializer

class RoutineView(View):
    # getRoutine
    def get(self, request):
        dummy_data = {
            'name': '죠르디',
            'type': '공룡',
            'job': '편의점알바생',
            'age': 5
        }
        return JsonResponse(dummy_data)

    # setRoutine
    def post(self, request):
        return HttpResponse("Post 요청을 잘받았다")

    # updateRoutine
    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")

    # deleteRoutine
    def delete(self, request):
        return HttpResponse("Delete 요청을 잘받았다")
    
class WindowView(View):
    # getStatus
    def get(self, request):
        dummy_data = {
            'name': '죠르디',
            'type': '공룡',
            'job': '편의점알바생',
            'age': 5
        }
        return JsonResponse(dummy_data)
    
    # controlWindow ??
    def post(self, request):
        return HttpResponse("Post 요청을 잘받았다")

    # (arduino)updateStatus
    def put(self, request):
        return HttpResponse("Put 요청을 잘받았다")
