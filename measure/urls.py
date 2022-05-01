from django.urls import path

from . import views

app_name = 'measure'
urlpatterns = [
    path('measure', views.MeasureView.as_view(), name='measure'),
]