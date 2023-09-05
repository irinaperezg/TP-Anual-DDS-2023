package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

@Entity
@Getter @Setter
@Table(name = "miembro")
public class Miembro extends Persistente {

  @OneToOne
  private Persona persona;

  @Enumerated
  private TipoMiembro tipo;

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
