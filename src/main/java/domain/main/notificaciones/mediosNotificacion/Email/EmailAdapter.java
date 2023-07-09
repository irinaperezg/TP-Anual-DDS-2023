package domain.main.notificaciones.mediosNotificacion.Email;

import domain.usuarios.Persona;

public interface EmailAdapter {
  void mandar(String mensaje, String email);
}
