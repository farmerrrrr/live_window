from django.urls import path

from . import views

app_name = 'account'
urlpatterns = [
    path('/user', views.UserView.as_view(), name='index'),
    path('/device', views.DeviceView.as_view(), name='index'),
]