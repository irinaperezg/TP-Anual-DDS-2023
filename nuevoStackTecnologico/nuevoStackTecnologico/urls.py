from django.urls import path
from .views import listar_incidentes

urlpatterns = [
    path('incidentes/', listar_incidentes)
]
