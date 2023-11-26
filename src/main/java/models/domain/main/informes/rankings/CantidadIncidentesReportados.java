package models.domain.main.informes.rankings;

import models.domain.main.entidades.Entidad;
import models.domain.main.incidentes.Incidente;
import models.domain.main.informes.PosicionRanking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CantidadIncidentesReportados implements Ranking {
  public CantidadIncidentesReportados() {
  }

  public List<PosicionRanking> elaborarRanking(List<Entidad> entidades){
    List<PosicionRanking> posiciones = new ArrayList<>();

    for (Entidad entidad : entidades)
    {
      int cantidad = entidad.obtenerIncidentesSemanales().size();
      PosicionRanking posicionRanking = new PosicionRanking(cantidad, entidad);
      posiciones.add(posicionRanking);
    }

    return posiciones;
  }

  public String getDenominacion()
  {
    return "Cantidad de incidentes reportados";
  }
}
