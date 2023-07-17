package domain.main.informes;

import domain.main.entidades.Entidad;

import java.util.List;

public interface Ranking {
  public List<String> elaborarRanking(List<Entidad> entidades);
}
