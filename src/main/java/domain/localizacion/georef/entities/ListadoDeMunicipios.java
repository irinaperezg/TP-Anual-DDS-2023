package domain.localizacion.georef.entities;

import domain.localizacion.main.localizaciones.Municipio;
import lombok.Getter;

import java.util.List;

public class ListadoDeMunicipios {
  @Getter
  public List<Municipio> municipios;
}