package domain.main.informes;

import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Rankeador {
  /*public List<Entidad> elaborarRankingPromedioCierre(List<Entidad> entidades){
    List<Entidad> ranking = entidades.stream()
            .sorted(Comparator.comparingLong(Entidad::obtenerPromedioCierreIncidentes).reversed())
        .collect(Collectors.toList());
    return ranking;
  }

  public List<Entidad> elaborarRankingCantidadIncidentesReportados(List<Entidad> entidades){
    List<Entidad> ranking = entidades.stream()
        .sorted(Comparator.comparingInt((Entidad entidad) -> entidad.obtenerIncidentesSemanales()
            .stream().filter(incidente -> !incidente.esReciente())
            .toList().size()).reversed()).collect(Collectors.toList());
    return ranking;
  }
  public List<Incidente> elaborarRankingGradoImpactoProblematicas(List<Incidente> incidentes){
  List<Incidente> ranking = incidentes.stream().sorted(Comparator.
      comparingInt((Incidente incidente) -> incidente.calcularImpactoSobreComunidad())
      .reversed()).collect(Collectors.toList());
  return ranking;
  }

*/

}
