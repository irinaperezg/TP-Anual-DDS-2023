package domain.main.notificaciones;

import domain.main.notificaciones.frecuenciasNotificacion.Notificacion;
import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;


public class Notificador {
  private static Notificador instancia;
  @Getter @Setter
  private static WhatsappAdapter whatsappAdapter;
  private static EmailAdapter emailAdapter;

  private Notificador() {
  }

  public static Notificador obtenerInstancia() { // Singleton
    if (instancia == null){
      instancia = new Notificador();
      whatsappAdapter = new TwilioWpp();
      emailAdapter = new JavaxMail();
    }
    return instancia;
  }

  public void enviarNotificacion(Notificacion notificacion) {
    switch (notificacion.getDestinatario().getPreferenciaMedioNotificacion()){
      case WHATSAPP :
        whatsappAdapter.mandar(notificacion);
        break;

      case EMAIL:
        emailAdapter.mandar(notificacion);
        break;

      default:
        //TODO excepcion
        break;
    }

  }

  /*
  public void notificarProblematicas(Delegado delegado) {
    String mensaje = "PROBLEMATICAS"; //TODO
    String email = delegado.getEmail();
    String nombre = delegado.getNombre();
    mandarNotificacion(email, nombre, mensaje);
  }*/

}
