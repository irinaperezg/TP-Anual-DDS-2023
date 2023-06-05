package domain.localizacion.georef.entities.entities;
import domain.localizacion.Municipio;

import java.util.List;
public class ListadoDeMunicipios {
  public int cantidad;
  public int total;
  public int inicio;
  public Parametro parametros;
  public List<Municipio> municipios;

  public List<Municipio> getMunicipios() {
    return municipios;
  }

  private class Parametro {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }
}