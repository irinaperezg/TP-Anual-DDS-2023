package domain.main.informes;

import domain.main.EntidadPrestadora;
import domain.main.entidades.Entidad;
import java.util.List;

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
    List<String> rankingPromedioCierre = Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades);
    List<String> rankingCantidadIncidentes = Rankeador.obtenerInstancia().elaborarRankingCantidadIncidentesReportados(entidades);
    List<String> rankingMayorImpacto = Rankeador.obtenerInstancia().elaborarRankingGradoImpactoProblematicas(entidades);

    this.adapter.generarInforme(entidadPrestadora.getDenominacion(), rankingPromedioCierre, rankingCantidadIncidentes, rankingMayorImpacto);
  }
  public void generarInformesParaEntidadesPrestadoras(List<EntidadPrestadora> entidadesPrestadoras) {
    for(EntidadPrestadora entidadPrestadora : entidadesPrestadoras) {
      generarInforme(entidadPrestadora);
    }
  }
}
