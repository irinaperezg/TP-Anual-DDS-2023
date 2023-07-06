package domain.main.notificaciones.mediosNotificacion;

import domain.usuarios.Persona;

public interface PreferenciaMedioNotificacion {

  void notificar(Persona persona, String Mensaje);
}
