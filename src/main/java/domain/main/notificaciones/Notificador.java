package domain.main.notificaciones;

import domain.main.PrestacionDeServicio;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.usuarios.Delegado;
import domain.usuarios.Persona;

import java.util.List;

public class Notificador {

  //TODO: INTENTAR UNIFICAR ESTOS DOS METODOS DE ACA ABAJO
  public void notificarAperturaIncidente(List<Persona> personas, Incidente incidente) {
    for(Persona persona:personas) {
      FrecuenciaNotificacion frecuenciaNotificationPersona = persona.getFrecuenciaNotificacion();
      PrestacionDeServicio prestacion;
      //TODO QUE LE QUEREMOS PASAR A LA PERSONA

      frecuenciaNotificationPersona.gestionarApertura(persona, incidente);
    }
  }

  public void notificarCierreIncidente(List<Persona> personas, Incidente incidente) {
    for(Persona persona:personas) {
      FrecuenciaNotificacion frecuenciaNotificationPersona = persona.getFrecuenciaNotificacion();
      PrestacionDeServicio prestacion;
      //TODO QUE LE QUEREMOS PASAR A LA PERSONA

      frecuenciaNotificationPersona.gestionarCierre(persona, incidente);
    }
  }
  /*
  public void notificarIncidenteOArreglo(boolean esIncidente, List<Miembro> miembros, String servicio, String entidad, String establecimiento, TipoEntidad tipoEntidad) {
    String inicioMensaje;
    if (esIncidente) {
      inicioMensaje = "Hubo un incidente en ";
    } else inicioMensaje = "Se arreglo ";
    String mensaje = inicioMensaje + "el servicio " + servicio + " en " + tipoEntidad.getTipoEntidad() + " " + entidad + " " + tipoEntidad.getTipoEstablecimiento() + " " + establecimiento;

    for (Miembro miembro : miembros) {
      mandarNotificacion(miembro.getEmail(), miembro.getNombre(), mensaje);
    }
  }*/




  public void notificarProblematicas(Delegado delegado) {
    String mensaje = "PROBLEMATICAS"; //TODO
    String email = delegado.getEmail();
    String nombre = delegado.getNombre();
    mandarNotificacion(email, nombre, mensaje);
  }


  private void mandarNotificacion(String email, String nombre, String mensaje) {
    //TODO
    System.out.println("Nombre: " + nombre + " --- Mensaje: " + mensaje);
  }

}
