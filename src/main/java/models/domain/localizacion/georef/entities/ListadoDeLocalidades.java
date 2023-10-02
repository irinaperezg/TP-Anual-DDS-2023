package models.domain.localizacion.georef.entities;

import models.domain.localizacion.main.Localidad;
import lombok.Getter;

import java.util.List;

public class ListadoDeLocalidades {
  @Getter
  public List<Localidad> localidades;
}
