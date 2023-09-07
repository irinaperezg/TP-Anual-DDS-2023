package domain.localizacion.main;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.Embeddable;
@Getter
@Embeddable
public class Localidad {
  @Column
  private final Integer id;
  @Column
  private final String nombre;
  @Transient
  private final Localizacion localizacion;

  public Localidad(Integer id, String nombre, Localizacion localizacion) {
    this.id = id;
    this.nombre = nombre;
    this.localizacion = localizacion;
  }

  public Localidad() {
    this.id = null;
    this.nombre = null;
    this.localizacion = null;
  }

  public boolean esIgualA(Localidad localidad) {
    return this.id.equals(localidad.getId()) && this.nombre.equals(localidad.getNombre()) && this.localizacion.esIgualA(localidad.getLocalizacion());
  }
}
