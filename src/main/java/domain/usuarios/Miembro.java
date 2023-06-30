package domain.usuarios;

import domain.localizacion.main.Localidad;
import lombok.Getter;
import lombok.Setter;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

public class Miembro {
  @Getter
  private Persona persona;
  @Getter @Setter
  private TipoMiembro tipo;
  @Getter
  private Comunidad comunidad;

  public void modificarTipoMiembro () {
    if(tipo == OBSERVADOR) setTipo(AFECTADO);
    else setTipo(OBSERVADOR);
  }
}
