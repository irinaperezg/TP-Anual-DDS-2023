package domain.main.informes;

import domain.main.EntidadPrestadora;
import domain.main.entidades.Entidad;
import domain.main.informes.rankings.Rankeador;
import domain.main.informes.rankings.Ranking;

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

  public void generarInforme(EntidadPrestadora entidadPrestadora) {

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
  public void generarInformesParaEntidadesPrestadoras(List<EntidadPrestadora> entidadesPrestadoras) {
    for(EntidadPrestadora entidadPrestadora : entidadesPrestadoras) {
      generarInforme(entidadPrestadora);
    }
  }
}
