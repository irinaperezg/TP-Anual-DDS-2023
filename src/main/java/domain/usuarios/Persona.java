package domain.usuarios;

import domain.localizacion.main.Localidad;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Persona {
  private Usuario usuario;
  private List<Miembro> miembros;
  @Setter
  private Localidad localidad = null;
  private String email;
  private String telefono;
  private FrecuenciaNotificacion frecuenciaNotification;
  private PreferenciaMedioNotificacion preferenciaMedioNotificacion;

  public Persona(Usuario usuario, String email, String telefono, FrecuenciaNotificacion frecuenciaNotification, PreferenciaMedioNotificacion preferenciaMedioNotificacion) {
    this.usuario = usuario;
    this.email = email;
    this.telefono = telefono;
    this.frecuenciaNotification = frecuenciaNotification;
    this.preferenciaMedioNotificacion = preferenciaMedioNotificacion;
  }

  public List<Comunidad> getComunidades() {
    return miembros.stream().map(miembro -> miembro.getComunidad()).toList();
  }
}
