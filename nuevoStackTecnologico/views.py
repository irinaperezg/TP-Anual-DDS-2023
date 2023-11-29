from django.shortcuts import render
from .models import Incidente
from .views import listar_incidentes

def listar_incidentes(request):
    incidentes = Incidente.objects.all()
    return render(request, 'listar_incidentes.html', {'incidentes': incidentes})
