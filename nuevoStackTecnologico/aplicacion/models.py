from django.db import models

class Servicio(models.Model):
    descripcion = models.TextField()

class Entidad(models.Model):
    denominacion = models.TextField()

class Establecimiento(models.Model):
    denominacion = models.TextField()
    entidad = models.ForeignKey(Entidad)

class PrestacionDeServicio(models.Model):
    servicio = models.ForeignKey(Servicio)
    establecimiento = models.ForeignKey(Establecimiento)

class Incidente(models.Model):
    observaciones = models.TextField()
    denominacion = models.TextField()
    prestacion = models.ForeignKey(PrestacionDeServicio)
    fechaApertura = models.DateTimeField()
    abierto = models.BooleanField(default=True)

    def __str__(self):
        return self.denominacion