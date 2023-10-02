package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;

import java.util.Comparator;
import java.util.List;

public class PromedioCierre implements Ranking {

  public PromedioCierre() {
  }
  public List<String> elaborarRanking(List<Entidad> entidades) {
    return entidades.stream()
        .sorted(Comparator.comparingLong(Entidad::obtenerPromedioCierreIncidentes).reversed())
        .toList().stream().map(Entidad::getDenominacion).toList();
  }

  public String getDenominacion()
  {
    return "Promedio de cierre de incidente";
  }
}
