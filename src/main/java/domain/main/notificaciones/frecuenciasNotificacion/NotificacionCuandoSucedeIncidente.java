package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.Establecimiento;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;

public class NotificacionCuandoSucedeIncidente implements FrecuenciaNotificacion {
  public void gestionarInicidente(Persona persona, Incidente incidente) {
    Notificacion notificacion = new Notificacion(persona, incidente);
    Notificador.obtenerInstancia().enviarNotificacion(notificacion);
  }

}
