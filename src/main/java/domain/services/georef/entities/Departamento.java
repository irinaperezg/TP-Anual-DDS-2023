package domain.services.georef.entities;

import domain.establecimientos.Localizacion;

public class Departamento extends Localizacion {
  public int id;
  public String nombre;

  public Departamento(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }
}
