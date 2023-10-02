package models.domain.main.informes;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.util.List;

public interface PDFAdapter {
  PDDocument generarInforme(String DenominacionEntidadPrestadora, List<String> ranking1, List<String> ranking2, List<String> ranking3);
}
