from django.shortcuts import render
from .models import Incidente

# en realidad creo q depende de los incidentes de que comunidad estariamos viendo?
def listar_incidentes(request):
    incidentes = Incidente.objects.all()
    return render(request, 'listar_incidentes.html', {'incidentes': incidentes})