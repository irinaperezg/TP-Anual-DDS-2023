package domain.main;

import domain.localizacion.main.Localidad;
import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="establecimiento")
public class Establecimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Column(name="denominacion", columnDefinition = "TEXT")
  private String denominacion;
  //@OneToOne
  //@JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  @Setter
  @Embedded
  private Localidad localidad = null;

  @Getter
  @OneToMany
  private final List<PrestacionDeServicio> prestaciones = new ArrayList<>();

  public Establecimiento() {
    this.denominacion = null;
    this.entidad = null;
  }

  public List<Incidente> obtenerIncidentesTotales() {
    return prestaciones.stream()
        .flatMap(prestacion -> prestacion.getIncidentes().stream())
        .collect(Collectors.toList());
  }

  public Establecimiento(Entidad entidad, String denominacion) {
    this.entidad = entidad;
    this.denominacion = denominacion;
  }
  public List<Persona> buscarInteresados(Servicio servicio) {
    return this.entidad.buscarInteresados(this.localidad, servicio);
  }
}