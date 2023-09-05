package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

@Entity
@Table(name="miembro")
public class Miembro extends Persistente {
  @Getter @Setter
  @OneToOne
  private Persona persona;
  @Getter @Setter
  @Enumerated
  private TipoMiembro tipo;
  @Setter
  @OneToOne
  private Comunidad comunidad;

  public Miembro(Persona persona, Comunidad comunidad) {
    this.setPersona(persona);
    this.setComunidad(comunidad);
  }

  public void modificarTipoMiembro () {
    if(tipo == OBSERVADOR) setTipo(AFECTADO);
    else setTipo(OBSERVADOR);
  }
}
