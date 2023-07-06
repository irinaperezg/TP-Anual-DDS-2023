package domain.main.notificaciones.mediosNotificacion.Email;

import domain.usuarios.Persona;

public interface EmailAdapter {
  void notificar(Persona persona, String Mensaje);
}
