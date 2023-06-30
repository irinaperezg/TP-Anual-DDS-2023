package domain.usuarios;

import domain.localizacion.main.Localidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

@Getter @Setter
public class Persona {
  private Usuario usuario;
  private List<Miembro> miembros;
  private Localidad localidad = null;


  public String getNombre() {
    return this.usuario.getNombre();
  }

  public String getEmail() {
    return this.usuario.getEmail();
  }

  public List<Comunidad> getComunidades() {
    return miembros.stream().map(miembro -> miembro.getComunidad()).toList();
  }

}
