package domain.main;

import domain.main.entidades.Entidad;
import domain.main.entidades.TipoEntidad;
import domain.main.incidentes.Incidente;
import domain.main.informes.ApachePDFBox;
import domain.main.informes.GeneradorDeInformes;
import domain.main.informes.Rankeador;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioBase;
import domain.usuarios.Comunidad;
import domain.usuarios.Delegado;
import io.jsonwebtoken.lang.Assert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneradorDeInformesTest {
  private static Incidente incidente1;
  private static Incidente incidente2;
  private static Entidad entidad1;
  private static Entidad entidad2;
  private static Comunidad comunidad1;
  private static PrestacionDeServicio prestacion1;
  private static EntidadPrestadora entidadPrestadora;

  @BeforeAll
  public static void init() {
    Servicio servicio = new ServicioBase("baño sin genero");
    TipoEntidad tipoEntidad = new TipoEntidad();

    entidad1 = new Entidad(tipoEntidad, "entidad1");
    entidad2 = new Entidad(tipoEntidad, "entidad2");

    Establecimiento establecimiento1 = new Establecimiento(entidad1, "Banco Nacion");
    prestacion1 = new PrestacionDeServicio(establecimiento1, servicio);
    Establecimiento establecimiento2 = new Establecimiento(entidad2, "Banco Provincia");
    PrestacionDeServicio prestacion2 = new PrestacionDeServicio(establecimiento2, servicio);

    establecimiento1.getPrestaciones().add(prestacion1);
    establecimiento2.getPrestaciones().add(prestacion2);

    entidad1.getEstablecimientos().add(establecimiento1);
    entidad2.getEstablecimientos().add(establecimiento2);
    comunidad1 = new Comunidad();

    incidente1 = new Incidente("observaciones 1", "incidente 1", comunidad1, prestacion1);
    incidente2 = new Incidente("observaciones 2", "incidente 2", comunidad1, prestacion2);

    prestacion1.getIncidentes().add(incidente1);
    prestacion2.getIncidentes().add(incidente2);

    Delegado delegado = new Delegado("ejemplo1", "ej1@gmail.com");

    entidadPrestadora = new EntidadPrestadora("Santander Rio", delegado);

    entidadPrestadora.setEntidades(Arrays.asList(entidad1, entidad2));

  }

  @Test
  @DisplayName("Se crea un nuevo informe con apachePDFBox")
  public void generarInforme() {
    ApachePDFBox apachePDFBox = new ApachePDFBox();

    List<Entidad> entidades = entidadPrestadora.getEntidades();
    List<String> rankingPromedioCierre = Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades);
    List<String> rankingCantidadIncidentes = Rankeador.obtenerInstancia().elaborarRankingCantidadIncidentesReportados(entidades);
    List<String> rankingMayorImpacto = Rankeador.obtenerInstancia().elaborarRankingGradoImpactoProblematicas(entidades);

    PDDocument informe = apachePDFBox.generarInforme(entidadPrestadora.getDenominacion(), rankingPromedioCierre, rankingCantidadIncidentes, rankingMayorImpacto);
    Assertions.assertNotNull(informe);
  }
}