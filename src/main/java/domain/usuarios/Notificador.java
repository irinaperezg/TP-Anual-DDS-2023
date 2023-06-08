package domain.usuarios;

import java.util.List;

public class Notificador {
  public void notificarIncidenteOArreglo(boolean esIncidente, List<Miembro> miembros, String servicio, String entidad, String establecimiento) {
    String inicioMensaje;
    if (esIncidente) {
      inicioMensaje = "Hubo un incidente en ";
    } else inicioMensaje = "Se arreglo ";
    String mensaje = inicioMensaje + "el servicio " + servicio + " en " + entidad + " " + establecimiento;
    for (Miembro miembro : miembros) {
      mandarNotificacion(miembro.getEmail(), miembro.getNombre(), mensaje);
    }
  }

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
