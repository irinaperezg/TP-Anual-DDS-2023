package models.domain.main.informes;

import models.domain.main.EntidadPrestadora;
import models.domain.main.entidades.Entidad;
import models.domain.main.informes.rankings.Rankeador;
import models.domain.main.informes.rankings.Ranking;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GeneradorDeInformes {
  private Rankeador rankeador;
  private PDFAdapter adapter;

  public void setAdapter(PDFAdapter adapter) {
    this.adapter = adapter;
  }
  private static GeneradorDeInformes instancia;

  private GeneradorDeInformes() {
  }

  public static GeneradorDeInformes obtenerInstancia() { // Singleton
    if (instancia == null){
      instancia = new GeneradorDeInformes();
    }
    return instancia;
  }

  public void generarInforme(EntidadPrestadora entidadPrestadora ) throws IOException {

    List <Entidad> entidades = entidadPrestadora.getEntidades();

    Optional<Ranking> resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Promedio de cierre de incidente")).findFirst();
    Ranking promedioCierre = resultado.get();

    resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Cantidad de incidentes reportados")).findFirst();
    Ranking cantidadIncidentes = resultado.get();

    resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Grado de impacto de las problematicas")).findFirst();
    Ranking gradoImpacto = resultado.get();

    List<String> rankingPromedioCierre = promedioCierre.elaborarRanking(entidades);
    List<String> rankingCantidadIncidentes = cantidadIncidentes.elaborarRanking(entidades);
    List<String> rankingMayorImpacto = gradoImpacto.elaborarRanking(entidades);

    this.adapter.generarInforme(entidadPrestadora.getDenominacion(), rankingPromedioCierre, rankingCantidadIncidentes, rankingMayorImpacto);
  }
  public void generarInformesParaEntidadesPrestadoras(List<EntidadPrestadora> entidadesPrestadoras) throws IOException {
    for(EntidadPrestadora entidadPrestadora : entidadesPrestadoras) {
      generarInforme(entidadPrestadora);
    }
  }
}
