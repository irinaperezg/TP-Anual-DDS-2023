package domain.main;

import domain.localizacion.main.Localidad;
import domain.main.entidades.Entidad;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

public class Establecimiento {
  private final String denominacion;
  private final Entidad entidad;
  @Setter
  private Localidad localidad = null;
  private final List<PrestacionDeServicio> prestaciones = new ArrayList<>();

  public Establecimiento(Entidad entidad, String denominacion) {
    this.entidad = entidad;
    this.denominacion = denominacion;
  }
  public List<Persona> buscarInteresados(Servicio servicio) {
    return this.entidad.buscarInteresados(this.localidad, servicio);
  }
}