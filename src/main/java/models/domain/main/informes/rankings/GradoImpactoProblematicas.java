package models.domain.main.informes.rankings;

import models.domain.apis.rankingImpactoIncidentes.ServicioRankingImpacto;
import models.domain.apis.rankingImpactoIncidentes.entities.RequestEntidadDTO;
import models.domain.apis.rankingImpactoIncidentes.entities.ResponseEntidadDTO;
import models.domain.main.entidades.Entidad;
import models.repositorios.EntidadRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GradoImpactoProblematicas implements Ranking {

  public GradoImpactoProblematicas() {}

  public List<String> elaborarRanking(List<Entidad> entidades) throws IOException {
    ServicioRankingImpacto servicioRankingImpacto = ServicioRankingImpacto.instancia();

    List<RequestEntidadDTO> requestsEntidadDTO = generarRequests(entidades);
    List<ResponseEntidadDTO> responseEntidadDTO = servicioRankingImpacto.generarRankingImpacto(requestsEntidadDTO);

    List<Integer> idsEntidades = responseEntidadDTO.stream().sorted(Comparator.comparingInt(entidad -> entidad.puestoRanking))
        .map(entidad -> entidad.idEntidad).toList();

    List<String> entidadesFinales = new ArrayList<>();
    EntidadRepository entidadRepository = new EntidadRepository();

    for (Integer idEntidad : idsEntidades) {
      String denominacionEntidad = entidadRepository.buscarPorID(idEntidad.longValue()).getDenominacion();
      entidadesFinales.add(denominacionEntidad);
    }

    return entidadesFinales;
  }

  private List<RequestEntidadDTO> generarRequests(List<Entidad> entidades) {
    List<RequestEntidadDTO> requests = new ArrayList<>();

    for(Entidad entidad : entidades) {
      RequestEntidadDTO request = new RequestEntidadDTO();
      request.idEntidad = entidad.getId().intValue();
      request.sumatoriaTiemposResolucion = entidad.obtenerSumatoriaTiemposResolucion();
      request.cantidadIncidentesNoResueltos = entidad.obtenerIncidentesNoResueltos();
      request.cantidadMiembrosAfectados = entidad.obtenerCantidaMiembrosAfectados();
      requests.add(request);
    }

    return requests;
  }

  public String getDenominacion()
  {
    return "Grado de impacto de las problematicas";
  }
}
