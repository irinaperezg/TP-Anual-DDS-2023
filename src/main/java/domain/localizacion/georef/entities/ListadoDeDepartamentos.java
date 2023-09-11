package domain.localizacion.georef.entities;

import domain.localizacion.main.Localizacion;
import lombok.Getter;

import java.util.List;

public class ListadoDeDepartamentos {
  @Getter
  public List<Localizacion> departamentos;
}
