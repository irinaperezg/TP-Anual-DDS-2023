package domain.main.notificaciones.mediosNotificacion.Email;

import domain.main.notificaciones.frecuenciasNotificacion.Notificacion;


public interface EmailAdapter {
  void mandar(Notificacion notificacion);
}
