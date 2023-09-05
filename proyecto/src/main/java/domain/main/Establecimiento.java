package domain.main;

import domain.localizacion.main.Localidad;
import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "Establecimiento")
public class Establecimiento {
  @Id
  @GeneratedValue
  private Long id;
  @Getter
  private final String denominacion;
  private final Entidad entidad;
  @Setter
  private Localidad localidad = null;
  @Getter
  private final List<PrestacionDeServicio> prestaciones = new ArrayList<>();

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