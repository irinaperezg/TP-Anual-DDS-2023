package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;

import java.util.Comparator;
import java.util.List;

public class CantidadIncidentesReportados implements Ranking {
  public CantidadIncidentesReportados() {
  }

  public List<String> elaborarRanking(List<Entidad> entidades){
    return entidades.stream()
        .sorted(Comparator.comparingInt((Entidad entidad) -> entidad.obtenerIncidentesSemanales()
            .stream().filter(incidente -> (!incidente.esReciente() && !incidente.isAbierto())).distinct().toList()
            .size()).reversed()).toList().stream().map(Entidad::getDenominacion).toList();
  }

  public String getDenominacion()
  {
    return "Cantidad de incidentes reportados";
  }
}
