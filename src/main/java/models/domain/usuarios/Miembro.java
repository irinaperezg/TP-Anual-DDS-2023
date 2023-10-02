package models.domain.usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "miembro")
public class Miembro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona persona;

  @Enumerated(EnumType.STRING)
  private TipoMiembro tipo;

  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;

  @Column(name = "es_administrador")
  private Boolean esAdministrador;

  public Miembro(Persona persona, Comunidad comunidad) {
    this.setPersona(persona);
    this.setComunidad(comunidad);
  }

  public Miembro() {

  }

  public void modificarTipoMiembro () {
    if(tipo == TipoMiembro.OBSERVADOR) setTipo(TipoMiembro.AFECTADO);
    else setTipo(TipoMiembro.OBSERVADOR);
  }
}
