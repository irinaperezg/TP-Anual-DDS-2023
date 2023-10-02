package models.domain.localizacion.georef.entities;

import models.domain.localizacion.main.Localizacion;
import lombok.Getter;

import java.util.List;

public class ListadoDeMunicipios {
  @Getter
  public List<Localizacion> municipios;
}