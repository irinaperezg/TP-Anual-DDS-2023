package domain.usuarios;

import domain.localizacion.main.Localidad;
import domain.main.notificaciones.frecuenciasNotificacion.Calendario;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import lombok.Getter;
import lombok.Setter;
import org.quartz.SchedulerException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Persona {
  private Usuario usuario;
  private List<Miembro> miembros;
  private Localidad localidad = null;
  private String email;
  private String telefono;
  private FrecuenciaNotificacion frecuenciaNotification;
  private PreferenciaMedioNotificacion preferenciaMedioNotificacion;
  private List<LocalDateTime> horariosDeNotificaciones;

  public Persona(Usuario usuario, String email, String telefono, FrecuenciaNotificacion frecuenciaNotification, PreferenciaMedioNotificacion preferenciaMedioNotificacion, List<LocalDateTime> horariosDeNotificaciones) throws SchedulerException {
    this.usuario = usuario;
    this.email = email;
    this.telefono = telefono;
    this.frecuenciaNotification = frecuenciaNotification;
    this.preferenciaMedioNotificacion = preferenciaMedioNotificacion;
    this.horariosDeNotificaciones = horariosDeNotificaciones;
  }

  public List<Comunidad> getComunidades() {
    return miembros.stream().map(miembro -> miembro.getComunidad()).toList();
  }
}
