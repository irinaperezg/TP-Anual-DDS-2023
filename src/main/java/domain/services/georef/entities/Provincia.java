package domain.services.georef.entities;

import domain.establecimientos.Localizacion;

public class Provincia extends Localizacion {
  public int id;
  public String nombre;

  public Provincia(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }
}