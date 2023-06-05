package domain.usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.entidades.Entidad;
import domain.localizacion.Localizacion;
import domain.entidades.Servicio;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;
  private String email;
  private List<Servicio> servicios = new ArrayList();
  private List<Entidad> entidades = new ArrayList();
  @Setter
  private Localizacion localizacion = null;


  public Usuario(String nombre, String contraseniaEncriptada, String email) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
    this.email = email;
  }

  public void agregarServicios(Servicio ... unosServicios) {
    servicios.addAll(Arrays.stream(unosServicios).toList());
  }

  public boolean leInteresaUna(Entidad entidad) {
    return entidad.tuvoIncidente(servicios) || entidad.estaCerca(localizacion);
  }

}
