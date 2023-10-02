package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;
import models.domain.main.incidentes.Incidente;

import java.util.Comparator;
import java.util.List;

public class GradoImpactoProblematicas implements Ranking {

  public GradoImpactoProblematicas() {
  }

  public List<String> elaborarRanking(List<Entidad> entidades){
    return entidades.stream().flatMap(entidad -> entidad.obtenerIncidentesSemanales().stream())
        .toList().stream().sorted(Comparator.comparingInt(Incidente::calcularImpactoSobreComunidad)
            .reversed()).toList().stream().map(Incidente::getDenominacion).toList();
  }

  public String getDenominacion()
  {
    return "Grado de impacto de las problematicas";
  }
}
