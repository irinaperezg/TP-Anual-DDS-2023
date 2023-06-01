package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.establecimientos.Localizacion;
import domain.establecimientos.Servicio;
import domain.services.georef.entities.Departamento;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.usuarios.Delegado;
import domain.usuarios.Usuario;

import java.util.List;

public class Entidad {
  private String denominacion;
  private List<Establecimiento> establecimientos;

  public boolean tuvoIncidente (List<Servicio> servicios){
    return establecimientos.stream().anyMatch(establecimiento -> establecimiento.tuvoIncidente(servicios));
  }

  public boolean estaCerca(Localizacion localizacionConsultada) {
      return establecimientos.stream().anyMatch(establecimiento -> establecimiento.estaCerca(localizacionConsultada));
  }
}