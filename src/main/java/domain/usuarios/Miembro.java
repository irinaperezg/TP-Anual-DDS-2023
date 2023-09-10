package domain.usuarios;

import domain.Persistente;
import domain.main.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static domain.usuarios.TipoMiembro.AFECTADO;
import static domain.usuarios.TipoMiembro.OBSERVADOR;

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
    if(tipo == OBSERVADOR) setTipo(AFECTADO);
    else setTipo(OBSERVADOR);
  }
}
