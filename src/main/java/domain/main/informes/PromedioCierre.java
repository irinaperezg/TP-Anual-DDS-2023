package domain.main.informes;

import domain.main.entidades.Entidad;

import java.util.Comparator;
import java.util.List;

public class PromedioCierre implements Ranking{
  public List<String> elaborarRanking(List<Entidad> entidades) {
    return entidades.stream()
        .sorted(Comparator.comparingLong(Entidad::obtenerPromedioCierreIncidentes).reversed())
        .toList().stream().map(Entidad::getDenominacion).toList();
  }
}
