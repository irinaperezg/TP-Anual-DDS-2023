package models.domain.localizacion.georef.entities;

import models.domain.localizacion.main.Provincia;
import lombok.Getter;

import java.util.List;

public class ListadoDeProvincias {
  @Getter
  public List<Provincia> provincias;
}