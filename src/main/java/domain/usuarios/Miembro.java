package domain.usuarios;

import domain.entidades.Entidad;
import domain.entidades.Servicio;
import domain.localizacion.Localizacion;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Miembro {
  private Usuario usuario;
  private List<Comunidad> comunidades;
  private List<Servicio> servicios = new ArrayList();
  private List<Entidad> entidades = new ArrayList();
  @Setter
  private Localizacion localizacion = null;

  public Miembro(Usuario usuario) {
    this.usuario = usuario;
  }

  public void agregarServicios(Servicio ... unosServicios) {
    servicios.addAll(Arrays.stream(unosServicios).toList());
  }

  public boolean leInteresaUna(Entidad entidad) {
    return entidad.tuvoIncidente(servicios) || entidad.estaCerca(localizacion);
  }
}
