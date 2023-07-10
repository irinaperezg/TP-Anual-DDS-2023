package domain.main.entidades;

import domain.localizacion.main.Localidad;
import domain.main.EntidadPrestadora;
import domain.main.Establecimiento;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Entidad {
  @Getter
  private TipoEntidad tipo;
  @Getter
  private String denominacion;
  private EntidadPrestadora entidadPrestadora;
  private final List<Persona> asociados = new ArrayList<>();
  @Getter @Setter
  private final List<Establecimiento> establecimientos = new ArrayList<>();

  public Entidad(TipoEntidad tipo, String denominacion) {
    this.tipo = tipo;
    this.denominacion = denominacion;
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
    List<Incidente> incidentes = this.obtenerIncidentesSemanales();
    long totalSegundos = incidentes.stream().mapToLong(incidente -> incidente.calcularTiempoCierre().toSeconds()).sum();
    long cantidadIncidentes = incidentes.size();
    return totalSegundos / cantidadIncidentes;
  }

  public void agregarAsociados(Persona ... nuevosAsociados) {
    asociados.addAll(List.of(nuevosAsociados));
  }
}