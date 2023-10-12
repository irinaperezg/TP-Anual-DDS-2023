package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;

import java.io.IOException;
import java.util.List;

public interface Ranking {

  public List<String> elaborarRanking(List<Entidad> entidades) throws IOException;
  public String getDenominacion();
}
