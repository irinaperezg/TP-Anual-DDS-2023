package domain.main.informes.rankings;

import domain.main.entidades.Entidad;
import lombok.Getter;

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
