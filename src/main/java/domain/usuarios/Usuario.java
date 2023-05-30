package domain.usuarios;

import java.util.List;

import domain.entidades.Entidad;
import domain.establecimientos.Localizacion;
import domain.establecimientos.Servicio;
import domain.services.georef.entities.Departamento;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import lombok.Getter;


@Getter
public class Usuario {
  private int id;
  private String nombre;
  private String contraseniaEncriptada;
  private String email;
  private List<Servicio> servicios;
  private List<Entidad> entidades;
  private Localizacion localizacion;


  public Usuario(String nombre, String contraseniaEncriptada) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
  }

  public boolean leInteresaUna(Entidad entidad) {
    return entidad.tuvoIncidente(servicios) || entidad.estaCerca(localizacion);
  }

}
