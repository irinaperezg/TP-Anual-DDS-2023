package domain.localizacion.main;

import lombok.Getter;

@Getter
public class Localidad {
  private final Integer id;
  private final String nombre;
  private final Localizacion localizacion;

  public Localidad(Integer id, String nombre, Localizacion localizacion) {
    this.id = id;
    this.nombre = nombre;
    this.localizacion = localizacion;
  }

  public boolean esIgualA(Localidad localidad) {
    return this.id.equals(localidad.getId()) && this.nombre.equals(localidad.getNombre()) && this.localizacion.esIgualA(localidad.getLocalizacion());
  }
}
