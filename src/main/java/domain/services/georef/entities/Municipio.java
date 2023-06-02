package domain.services.georef.entities;

import domain.establecimientos.Localizacion;

public class Municipio extends Localizacion {
  public int id;
  public String nombre;

  public Municipio(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }
}
