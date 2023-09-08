package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.Establecimiento;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;

import javax.persistence.*;

//@Entity
//@DiscriminatorValue("CUANDO_SUCEDE_INCIDENTE")
public class NotificacionCuandoSucedeIncidente extends FrecuenciaNotificacionBase {

  public void gestionarInicidente(Persona persona, Incidente incidente) {
    Notificacion notificacion = new Notificacion(persona, incidente);
    Notificador.obtenerInstancia().enviarNotificacion(notificacion);
  }

}
