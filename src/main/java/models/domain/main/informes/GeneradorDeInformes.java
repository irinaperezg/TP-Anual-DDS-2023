package models.domain.main.informes;

import models.domain.main.EntidadPrestadora;
import models.domain.main.entidades.Entidad;
import models.domain.main.exportar.Informe;
import models.domain.main.exportar.PDFAdapter;
import models.domain.main.informes.rankings.Rankeador;
import models.domain.main.informes.rankings.Ranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

  public List<Informe> generarInforme(List <Entidad> entidades) throws IOException {
    List<Informe> informes = new ArrayList<>();
    List<String> rutas = new ArrayList<>();
    List<String> reportes = Arrays.asList("MAYOR_CANTIDAD_INCIDENTES", "PROMEDIO_CIERRE_INCIDENTES");
    //Esto de arriba se puede sacar de un archivo config

   /* Optional<Ranking> resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Promedio de cierre de incidente")).findFirst();
    Ranking promedioCierre = resultado.get();

    resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Cantidad de incidentes reportados")).findFirst();
    Ranking cantidadIncidentes = resultado.get();

    resultado = Rankeador.obtenerInstancia().getRankings().stream().
        filter(ranking -> ranking.getDenominacion().equals("Grado de impacto de las problematicas")).findFirst();
    Ranking gradoImpacto = resultado.get();*/

    /*List<PosicionRanking> rankingPromedioCierre = promedioCierre.elaborarRanking(entidades);
    List<PosicionRanking> rankingCantidadIncidentes = cantidadIncidentes.elaborarRanking(entidades);
    List<PosicionRanking> rankingMayorImpacto = gradoImpacto.elaborarRanking(entidades);*/
    //Esto de arriba ya no seria necesario

    for (String reporte : reportes) {
      Ranking ranking = FactoryStrategia.crear(reporte);
      Informe informe = new Informe(ranking.elaborarRanking(entidades));
      informes.add(informe);
      rutas.add(this.adapter.generarInforme(informe, reporte));
    }
    /*this.adapter.generarInforme(new Informe(rankingPromedioCierre));
    this.adapter.generarInforme(new Informe(rankingCantidadIncidentes));
    this.adapter.generarInforme(new Informe(rankingMayorImpacto))*/
    return informes; //Deberia devolver todas las rutas
  }
  public void generarInformesParaEntidadesPrestadoras(List<EntidadPrestadora> entidadesPrestadoras) throws IOException {
    for(EntidadPrestadora entidadPrestadora : entidadesPrestadoras) {
      generarInforme(entidadPrestadora.getEntidades());
    }
  }
}
