package domain.usuarios;

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

  public Miembro(Persona persona, Comunidad comunidad) {
    this.persona = persona;
    this.comunidad = comunidad;
  }

  public void modificarTipoMiembro () {
    if(tipo == OBSERVADOR) setTipo(AFECTADO);
    else setTipo(OBSERVADOR);
  }
}
