package domain.main.informes;

import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Rankeador {
  public List<Entidad> elaborarRankingPromedioCierre(List<Entidad> entidades){
    List<Entidad> ranking = entidades.stream()
        .sorted(Comparator.comparingLong(Entidad::obtenerPromedioCierreIncidentes).reversed())
        .collect(Collectors.toList());
    return ranking;
  }

// ME TIRA ERROR Y NO SE POR QUE
  public List<Entidad> elaborarRankingCantidadIncidentesReportados(List<Entidad> entidades){
      List<Entidad> ranking = entidades.stream()
          .sorted(Comparator.comparingInt(entidad -> entidad.obtenerIncidentesSemanales().size()).reversed())
          .collect(Collectors.toList());
    return ranking;
  }
  public List<Entidad> elaborarRankingGradoImpactoProblematicas(List<Incidente> entidades){
  // TODO
  }



}
