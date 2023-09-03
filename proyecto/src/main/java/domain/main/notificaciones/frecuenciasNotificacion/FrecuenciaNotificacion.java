package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.usuarios.Persona;

public interface FrecuenciaNotificacion {
  void gestionarInicidente (Persona persona, Incidente incidente);
}
