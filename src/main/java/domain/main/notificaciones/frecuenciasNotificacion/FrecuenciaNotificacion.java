package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.usuarios.Persona;

public interface FrecuenciaNotificacion {
  void gestionarApertura(Persona persona, Incidente incidente);
  void gestionarCierre(Persona persona, Incidente incidente);
}
