package domain.main.servicio;

import domain.main.OrganismoDeControl;
import domain.main.PrestacionDeServicio;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Servicio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Column(name="descripcion")
  private String descripcion;

  @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PrestacionDeServicio> prestacionesDeServicio = new ArrayList<>();

  @ManyToMany(mappedBy = "servicios")
  private List<ServicioCompuesto> servicioCompuesto = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "organismo_de_control_id", referencedColumnName = "id")
  private OrganismoDeControl organismoDeControl;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
          name = "Asociados_Servicio_Persona",
          joinColumns = { @JoinColumn(name = "servicio_id") },
          inverseJoinColumns = { @JoinColumn(name = "persona_id") }
  )
  private List<Persona> asociados = new ArrayList<>();

  public Servicio(String descripcion) {
    this.descripcion = descripcion;
  }

  public Servicio() {

  }

  public boolean esDeInteresPara(Persona persona) {
    return asociados.contains(persona);
  }

  public void agregarAsociado(Persona persona) {
    asociados.add(persona);
  }
}
