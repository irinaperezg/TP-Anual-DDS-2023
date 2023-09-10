package domain.localizacion.main;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Getter
@Embeddable
public class Provincia {
  @Column(name = "id_provincia")
  private Integer id;
  @Column(name = "nombre_provincia")
  private String nombre;

  public Provincia(Integer id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Provincia() {
    this.id = null;
    this.nombre = null;
  }

  public boolean esIgualA(Provincia provincia) {
    return this.id.equals(provincia.getId()) && this.nombre.equals(provincia.getNombre());
  }
}