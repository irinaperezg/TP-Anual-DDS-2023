package domain.main.informes;

import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;

import java.util.Comparator;
import java.util.List;

public class GradoImpactoProblematicas implements Ranking {
  public List<String> elaborarRanking(List<Entidad> entidades){
    return entidades.stream().flatMap(entidad -> entidad.obtenerIncidentesSemanales().stream())
        .toList().stream().sorted(Comparator.comparingInt(Incidente::calcularImpactoSobreComunidad)
            .reversed()).toList().stream().map(Incidente::getDenominacion).toList();
  }
}
