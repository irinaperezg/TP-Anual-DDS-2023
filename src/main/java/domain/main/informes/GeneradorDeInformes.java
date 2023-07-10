package domain.main.informes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.main.entidades.Entidad;

import java.io.FileOutputStream;
import java.util.List;


public class GeneradorDeInformes {
  private Rankeador rankeador;
  public static void main(List<Entidad> entidades) {
    List<String> rankingPromedioCierre = Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades);
    List<String> rankingCantidadIncidentes = Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades);
    // List<String> rankingMayorImpacto = Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades);

    try {
      Document documento = new Document();
      PdfWriter.getInstance(documento, new FileOutputStream("src/main/resources/informe.pdf"));
      documento.open();

      // RANKING 1
      Paragraph title1 = new Paragraph("Entidades con mayor promedio de tiempo de cierre de incidentes: ");
      documento.add(title1);

      for (String entidad : rankingPromedioCierre) {
        Paragraph paragraph = new Paragraph(entidad);
        documento.add(paragraph);
      }

      // RANKING 2
      Paragraph title2 = new Paragraph("Entidades con mayor cantidad de incidentes reportados en la semana: ");
      documento.add(title2);

      for (String entidad : rankingCantidadIncidentes) {
        Paragraph paragraph = new Paragraph(entidad);
        documento.add(paragraph);
      }

      // RANKING 3
      /*
      Paragraph title3 = new Paragraph("Comunidades con mayor grado de impacto de problemáticas: ");
      documento.add(title3);

      for (String entidad : rankingMayorImpacto) {
        Paragraph paragraph = new Paragraph(entidad);
        documento.add(paragraph);
      }
      */

      documento.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
