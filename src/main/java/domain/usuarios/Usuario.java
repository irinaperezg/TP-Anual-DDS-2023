package domain.usuarios;

import java.util.ArrayList;
import java.util.List;

import domain.entidades.Entidad;
import domain.establecimientos.Localizacion;
import domain.establecimientos.Servicio;
import lombok.Getter;


@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;
  private String email;
  private List<Servicio> servicios = new ArrayList();
  private List<Entidad> entidades = new ArrayList();
  private Localizacion localizacion = null;


  public Usuario(String nombre, String contraseniaEncriptada, String email) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
    this.email = email;
  }

  public boolean leInteresaUna(Entidad entidad) {
    return entidad.tuvoIncidente(servicios) || entidad.estaCerca(localizacion);
  }

}
