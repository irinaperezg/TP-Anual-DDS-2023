package domain.main.informes;

import domain.main.entidades.Entidad;

import java.util.Comparator;
import java.util.List;

public class CantidadIncidentesReportados implements Ranking {
  public List<String> elaborarRanking(List<Entidad> entidades){
    return entidades.stream()
        .sorted(Comparator.comparingInt((Entidad entidad) -> entidad.obtenerIncidentesSemanales()
            .stream().filter(incidente -> (!incidente.esReciente() && !incidente.isAbierto()))
            .toList().size()).reversed()).toList().stream().map(Entidad::getDenominacion).toList();
  }
}
