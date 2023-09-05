package domain.localizacion.georef.entities;

import domain.localizacion.main.Provincia;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class ListadoDeProvincias {
  @Getter
  public List<Provincia> provincias;
}