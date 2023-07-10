package domain.main.informes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class ApachePDFBox implements PDFAdapter{
  public PDDocument generarInforme(String denominacionEntidadPrestadora, List<String> rankingPromedioCierre, List<String> rankingCantidadIncidentes, List<String> rankingMayorImpacto) {
    int i = 1;
    try {
      // Create a new empty document
      PDDocument document = new PDDocument();

      // Create a blank page
      PDPage page = new PDPage(PDRectangle.A4);
      document.addPage(page);

      // Create a new content stream for the page
      PDPageContentStream contentStream = new PDPageContentStream(document, page);

      // Begin the content stream
      contentStream.beginText();

      // Set the font and font size
      contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

      // Set the position on the page for the text
      contentStream.newLineAtOffset(20, 750);

      // Write the text to the content stream
      contentStream.showText(denominacionEntidadPrestadora);
      contentStream.newLineAtOffset(10, -50);


      contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
      contentStream.showText("- Entidades con mayor promedio de tiempo de cierre de incidentes: ");

      contentStream.newLineAtOffset(10, -20);
      contentStream.setFont(PDType1Font.HELVETICA, 14);
      // Write the list items for the first subtitle
      for(String entidad : rankingPromedioCierre) {
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(i + ". " + entidad);
        i++;
      }
      contentStream.newLineAtOffset(-10, -30);

      contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
      contentStream.showText("- Entidades con mayor cantidad de incidentes reportados: ");

      i = 1;
      contentStream.newLineAtOffset(10, -20);
      contentStream.setFont(PDType1Font.HELVETICA, 14);
      for(String entidad : rankingCantidadIncidentes) {
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(i + ". " + entidad);
        i++;
      }
      contentStream.newLineAtOffset(-10, -30);

      contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
      contentStream.showText("- Incidentes con mayor grado de impacto: ");

      i = 1;
      contentStream.newLineAtOffset(10, -20);
      contentStream.setFont(PDType1Font.HELVETICA, 14);
      for(String entidad : rankingMayorImpacto) {
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(i + ". " + entidad);
        i++;
      }

      // End the content stream for the first page
      contentStream.endText();
      contentStream.close();

      // Save the document to a file
      document.save("example.pdf");

      // Close the document
      document.close();

      System.out.println("PDF created successfully.");

      return document;

    } catch (IOException e) {
      System.err.println("Error creating PDF: " + e.getMessage());
    }
    return null;
  }

}
