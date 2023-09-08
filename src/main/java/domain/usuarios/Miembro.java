package domain.usuarios;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

@Getter @Setter
//@Entity
//@Table(name = "miembro")
public class Miembro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;

  @Enumerated(EnumType.STRING)
  private TipoMiembro tipo;

  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  public Miembro(Persona persona, Comunidad comunidad) {
    this.setPersona(persona);
    this.setComunidad(comunidad);
  }

  public Miembro() {

  }

  public void modificarTipoMiembro () {
    if(tipo == OBSERVADOR) setTipo(AFECTADO);
    else setTipo(OBSERVADOR);
  }
}
