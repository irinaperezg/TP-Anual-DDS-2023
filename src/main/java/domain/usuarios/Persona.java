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
  private Localidad localidad = null;
  private String email;
  private String telefono;
  private FrecuenciaNotificacion frecuenciaNotification;
  private PreferenciaMedioNotificacion preferenciaMedioNotificacion;

  public List<Comunidad> getComunidades() {
    return miembros.stream().map(miembro -> miembro.getComunidad()).toList();
  }
  public Persona(String nombre, String contraseniaEncriptada, String email, String telefono) {
    this.email = email;
    this.telefono = telefono;
  }
}
