package domain.localizacion.main;

import lombok.Getter;

@Getter
public class Provincia {
  private final Integer id;
  private final String nombre;

  public Provincia(Integer id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public boolean esIgualA(Provincia provincia) {
    return this.id.equals(provincia.getId()) && this.nombre.equals(provincia.getNombre());
  }
}