package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.Establecimiento;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;

public class NotificacionCuandoSucedeIncidente implements FrecuenciaNotificacion {

  @Override
  public void gestionarInicidente(Persona persona, Incidente incidente) {
    String mensaje = incidente.generarMensaje();
    Notificador.obtenerInstancia().enviarNotificacion(persona, mensaje); // porque es un Singleton
  }

  @Override
  public void notificarIncidentes(Persona personaANotificar) {
    //NADA
  }

}
