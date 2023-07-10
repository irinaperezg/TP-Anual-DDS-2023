package domain.main.notificaciones;

import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import domain.usuarios.Persona;

public class Notificador {
  private static Notificador instancia;
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

  public void enviarNotificacion(Persona persona, String mensaje) {
    switch (persona.getPreferenciaMedioNotificacion()){
      case WHATSAPP :
        whatsappAdapter.mandar(mensaje, persona.getTelefono());
        break;

      case EMAIL:
        emailAdapter.mandar(mensaje, persona.getEmail());
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
