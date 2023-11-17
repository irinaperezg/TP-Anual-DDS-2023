from django.db import models

class PrestacionDeServicio(models.Model):
    # 
class Miembro(models.Model):
    # 

class Comunidad(models.Model):
    nombre = models.CharField(max_length=255)
    descripcion = models.CharField(max_length=255)
    estaActivo = models.BooleanField()
    miembros = models.ManyToManyField(Miembro, related_name='comunidad_miembros', blank=True)
    incidentes = models.ManyToManyField(Incidente, related_name='comunidad_incidentes', blank=True)
    serviciosObservados = models.ManyToManyField(Servicio, related_name='comunidades_servicios', blank=True)
    establecimientosObservados = models.ManyToManyField(Establecimiento, related_name='comunidades_establecimientos', blank=True)

class Incidente(models.Model):
    observaciones = models.TextField()
    denominacion = models.TextField()
    prestacion = models.ForeignKey(PrestacionDeServicio, on_delete=models.CASCADE)
    fechaApertura = models.DateTimeField(auto_now_add=True)
    fechaCierre = models.DateTimeField(null=True, blank=True)
    comunidad = models.ForeignKey(Comunidad, on_delete=models.CASCADE)
    abierto = models.BooleanField(default=True)
    creador = models.OneToOneField(Miembro, on_delete=models.CASCADE)

    def __str__(self):
        return self.denominacion
