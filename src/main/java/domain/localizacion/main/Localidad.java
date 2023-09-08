package domain.localizacion.main;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.Embeddable;
@Getter
@Entity
@Table(name = "localidad")
public class Localidad {
  @Id
  private Integer id;
  @Column(name = "nombre")
  private String nombre;
  @Embedded
  private Localizacion localizacion;

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
