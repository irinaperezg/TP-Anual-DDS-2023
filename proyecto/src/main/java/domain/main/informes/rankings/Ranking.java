package domain.main.informes.rankings;

import domain.main.entidades.Entidad;

import java.util.List;

public interface Ranking {
  public List<String> elaborarRanking(List<Entidad> entidades);
  public String getDenominacion();
}
