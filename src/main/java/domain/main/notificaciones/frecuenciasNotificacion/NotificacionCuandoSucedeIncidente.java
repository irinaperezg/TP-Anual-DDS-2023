package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import domain.usuarios.Persona;

public class NotificacionCuandoSucedeIncidente implements FrecuenciaNotificacion {
  public void gestionarApertura(Persona persona, Incidente incidente) {
    String mensaje = "";
    persona.getPreferenciaMedioNotificacion().notificar(persona, mensaje);
  }

  public void gestionarCierre(Persona persona, Incidente incidente) {

  }
}
