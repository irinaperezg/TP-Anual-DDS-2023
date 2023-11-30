from django.db import models

class PrestacionDeServicio(models.Model):
    servicio = ForeignKey(Servicio)
    establecimiento = ForeignKey(Establecimiento)

class Servicio(models.Model):
    descripcion = models.TextField()

class Establecimiento(models.Model):
    denominacion = models.TextField()
    entidad = models.ForeignKey(Entidad)

class Entidad(models.Model):
    denominacion = models.TextField()

class Incidente(models.Model):
    observaciones = models.TextField()
    denominacion = models.TextField()
    prestacion = models.ForeignKey(PrestacionDeServicio)
    fechaApertura = models.DateTimeField()
    abierto = models.BooleanField(default=True)

    def __str__(self):
        return self.denominacion
