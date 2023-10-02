package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;

import java.util.List;

public interface Ranking {
  public List<String> elaborarRanking(List<Entidad> entidades);
  public String getDenominacion();
}
