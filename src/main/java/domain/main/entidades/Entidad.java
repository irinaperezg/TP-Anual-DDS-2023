package domain.main.entidades;

import domain.Persistente;
import domain.localizacion.main.Localidad;
import domain.main.EntidadPrestadora;
import domain.main.Establecimiento;
import domain.main.OrganismoDeControl;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="entidad")
public class Entidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Embedded
  private TipoEntidad tipo;

  @Getter
  @Column(name="denominacion", columnDefinition = "TEXT")
  private String denominacion;

  @ManyToOne
  @JoinColumn(name = "entidad_prestadora_id", referencedColumnName = "id")
  private EntidadPrestadora entidadPrestadora;

  @Getter
  @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Establecimiento> establecimientos = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
          name = "Entidades_X_Organismo",
          joinColumns = { @JoinColumn(name = "entidad_id") },
          inverseJoinColumns = { @JoinColumn(name = "organismo_de_control_id") }
  )
  private final List<OrganismoDeControl> organismosDeControl = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
          name = "Asociados",
          joinColumns = { @JoinColumn(name = "entidad_id") },
          inverseJoinColumns = { @JoinColumn(name = "persona_id") }
  )
  private final List<Persona> asociados = new ArrayList<>();


  public Entidad(TipoEntidad tipo, String denominacion) {
    this.tipo = tipo;
    this.denominacion = denominacion;
  }

  public Entidad() {

  }

  public List<Persona> buscarInteresados(Localidad localidad, Servicio servicio) {
    List<Persona> interesados = new ArrayList<>();
    for(Persona persona : asociados) {
      if (persona.getLocalidad().esIgualA(localidad) && servicio.esDeInteresPara(persona)) {
        interesados.add(persona);
      }
    }
    return interesados;
  }

  public void agregarAsociado(Persona persona) {
    asociados.add(persona);
  }

  public List<Incidente> obtenerIncidentesTotales() {
    return establecimientos.stream()
        .flatMap(establecimiento -> establecimiento.obtenerIncidentesTotales().stream())
        .collect(Collectors.toList());
  }

  public List<Incidente> obtenerIncidentesSemanales() {
    return this.obtenerIncidentesTotales().stream().filter(Incidente::perteneceSemanaActual).toList();
  }

  public long obtenerPromedioCierreIncidentes() {
    List<Incidente> incidentes = this.obtenerIncidentesSemanales().stream().
        filter(incidente -> !incidente.isAbierto()).collect(Collectors.toList());
    long totalSegundos = incidentes.stream().mapToLong(incidente -> incidente.calcularTiempoCierre().toSeconds()).sum();
    long cantidadIncidentes = incidentes.size();
    if (cantidadIncidentes == 0)
    {
      return 0;
    }
    return totalSegundos / cantidadIncidentes;
  }

  public void agregarAsociados(Persona ... nuevosAsociados) {
    asociados.addAll(List.of(nuevosAsociados));
  }

}