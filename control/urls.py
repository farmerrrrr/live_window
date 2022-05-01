from django.urls import path

from . import views

app_name = 'control'
urlpatterns = [
    path('routine', views.RoutineView.as_view(), name='routine'),
    path('window', views.WindowView.as_view(), name='window'),
]