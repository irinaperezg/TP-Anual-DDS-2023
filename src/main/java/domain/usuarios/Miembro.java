package domain.usuarios;

import domain.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Miembro {
  private Usuario usuario;
  private List<Comunidad> comunidades;
  @Getter @Setter
  private Localizacion localizacion = null;

  public Miembro(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getNombre() {
    return this.usuario.getNombre();
  }

  public String getEmail() {
    return this.usuario.getEmail();
  }
}
