package models.domain.main;

import models.domain.main.localizacion.Localidad;
import models.domain.main.entidades.Entidad;
import models.domain.main.incidentes.Incidente;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name="establecimiento")
public class Establecimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="denominacion", columnDefinition = "TEXT")
  private String denominacion;

  @ManyToOne
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  @Setter
  @ManyToOne
  @JoinColumn(name = "localidad_id", referencedColumnName = "id")
  private Localidad localidad = null;

  @Getter
  @OneToMany(mappedBy = "establecimiento", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<PrestacionDeServicio> prestaciones = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "Asociados_Establecimientos_Comunidad",
      joinColumns = { @JoinColumn(name = "establecimiento_id") },
      inverseJoinColumns = { @JoinColumn(name = "comunidad_id") }
  )
  private List<Comunidad> comunidadesAsociadas = new ArrayList<>();

  public Establecimiento() {
    this.denominacion = null;
    this.entidad = null;
  }

  public Establecimiento(String denominacion, Entidad entidad, Localidad localidad) {
    this.denominacion = denominacion;
    this.entidad = entidad;
    this.localidad = localidad;
  }

  public List<Incidente> obtenerIncidentesTotales() {
    return prestaciones.stream()
        .flatMap(prestacion -> prestacion.getIncidentes().stream())
        .collect(Collectors.toList());
  }

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "Asociados_Establecimientos_Comunidad",
      joinColumns = { @JoinColumn(name = "establecimiento_id") },
      inverseJoinColumns = { @JoinColumn(name = "comunidad_id") }
  )
  private final List<Comunidad> comunidades = new ArrayList<>();

  public Establecimiento(Entidad entidad, String denominacion) {
    this.entidad = entidad;
    this.denominacion = denominacion;
  }

  public Integer obtenerCantidadMiembrosAfectados() {
    return comunidadesAsociadas.stream().mapToInt(Comunidad::obtenerCantidadMiembrosAfectados).sum();
  }
}