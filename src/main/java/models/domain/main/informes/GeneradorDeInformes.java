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
/*
public class GeneradorDeInformes {
  private Rankeador rankeador;
  private PDFAdapter adapter;

  public void setAdapter(PDFAdapter adapter) {
    this.adapter = adapter;
  }

  private GeneradorDeInformes() {
  }
  public List<Informe> generarInforme(List <Entidad> entidades) throws IOException {
    List<Informe> informes = new ArrayList<>();
    List<String> rutas = new ArrayList<>();
    List<String> reportes = Arrays.asList("MAYOR_CANTIDAD_INCIDENTES", "PROMEDIO_CIERRE_INCIDENTES");

    for (String reporte : reportes) {
      Ranking ranking = FactoryStrategia.crear(reporte);
      Informe informe = new Informe(ranking.elaborarRanking(entidades));
      informes.add(informe);
      rutas.add(this.adapter.generarInforme(informe));
    }

    return informes; //Deberia devolver todas las rutas
  }
  public void generarInformesParaEntidadesPrestadoras(List<EntidadPrestadora> entidadesPrestadoras) throws IOException {
    for(EntidadPrestadora entidadPrestadora : entidadesPrestadoras) {
      generarInforme(entidadPrestadora.getEntidades());
    }
  }
}*/
