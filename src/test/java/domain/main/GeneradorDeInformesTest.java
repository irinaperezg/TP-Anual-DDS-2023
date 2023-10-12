package domain.main;

import models.domain.main.EntidadPrestadora;
import models.domain.main.Establecimiento;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.entidades.Entidad;
import models.domain.main.entidades.TipoEntidad;
import models.domain.main.incidentes.Incidente;
import models.domain.main.informes.ApachePDFBox;
import models.domain.main.informes.rankings.Rankeador;
import models.domain.main.informes.rankings.Ranking;
import models.domain.main.notificaciones.frecuenciasNotificacion.NotificacionSinApuros;
import models.domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import models.domain.main.servicio.Servicio;
import models.domain.main.servicio.ServicioBase;
import models.domain.usuarios.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GeneradorDeInformesTest {
  private static EntidadPrestadora entidadPrestadora;

  @BeforeAll
  public static void init() throws SchedulerException {
    Servicio servicio = new ServicioBase("baño sin genero");
    TipoEntidad tipoEntidad = new TipoEntidad();

    List<LocalDateTime> listaHorarios = new ArrayList<>();
    NotificacionSinApuros notificacionSinApuros = new NotificacionSinApuros();

    Usuario usuario = new Usuario("pepe", "argento");
    Persona persona1 = new Persona(usuario, "ej1@gmail.com", "1234");
    persona1.setFrecuenciaNotification(notificacionSinApuros);
    persona1.setPreferenciaMedioNotificacion(PreferenciaMedioNotificacion.EMAIL);
    persona1.setHorariosDeNotificaciones(listaHorarios);

    Entidad entidad1 = new Entidad(tipoEntidad, "entidad1");
    Entidad entidad2 = new Entidad(tipoEntidad, "entidad2");

    Establecimiento establecimiento1 = new Establecimiento(entidad1, "Banco Nacion");
    PrestacionDeServicio prestacion1 = new PrestacionDeServicio(establecimiento1, servicio);
    Establecimiento establecimiento2 = new Establecimiento(entidad2, "Banco Provincia");
    PrestacionDeServicio prestacion2 = new PrestacionDeServicio(establecimiento2, servicio);

    establecimiento1.getPrestaciones().add(prestacion1);
    establecimiento2.getPrestaciones().add(prestacion2);

    entidad1.getEstablecimientos().add(establecimiento1);
    entidad2.getEstablecimientos().add(establecimiento2);
    Comunidad comunidad1 = new Comunidad();

    Miembro miembro1 = new Miembro(persona1, comunidad1);

    Incidente incidente1 = new Incidente("observaciones 1", "incidente 1", comunidad1, prestacion1, miembro1);
    Incidente incidente2 = new Incidente("observaciones 2", "incidente 2", comunidad1, prestacion2, miembro1);

    prestacion1.getIncidentes().add(incidente1);
    prestacion2.getIncidentes().add(incidente2);

    Delegado delegado = new Delegado("ejemplo1", "ej1@gmail.com");

    entidadPrestadora = new EntidadPrestadora("Santander Rio", delegado);

    entidadPrestadora.setEntidades(Arrays.asList(entidad1, entidad2));
  }

  @Test
  @DisplayName("Se crea un nuevo informe con apachePDFBox")
  public void generarInforme() throws IOException {
    ApachePDFBox apachePDFBox = new ApachePDFBox();

    List<Entidad> entidades = entidadPrestadora.getEntidades();

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

    PDDocument informe = apachePDFBox.generarInforme(entidadPrestadora.getDenominacion(), rankingPromedioCierre, rankingCantidadIncidentes, rankingMayorImpacto);
    Assertions.assertNotNull(informe);
  }
}