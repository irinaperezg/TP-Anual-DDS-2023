package domain.localizacion.georef.entities.entities;

import domain.localizacion.Departamento;

import java.util.List;

public class ListadoDeDepartamentos {
  public int cantidad;
  public int total;
  public int inicio;
  public ListadoDeDepartamentos.Parametro parametros;
  public List<Departamento> departamentos;

  public List<Departamento> getDepartamentos() {
    return departamentos;
  }

  private class Parametro {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }
}
