package domain.main;

import domain.localizacion.main.Localidad;
import domain.localizacion.main.Provincia;
import domain.localizacion.main.localizaciones.Municipio;
import domain.main.entidades.Entidad;
import domain.main.entidades.TipoEntidad;
import domain.main.incidentes.Incidente;
import domain.main.informes.Rankeador;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionCuandoSucedeIncidente;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioBase;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleComplejidad;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;

public class RankeadorTest {
  private static Incidente incidente1;
  private static Incidente incidente2;
  private static Entidad entidad1;
  private static Entidad entidad2;
  private static Comunidad comunidad1;
  private static PrestacionDeServicio prestacion1;

  @BeforeAll
  public static void init() throws SchedulerException {
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

    incidente1 = new Incidente("observaciones 1", comunidad1, prestacion1);
    incidente2 = new Incidente("observaciones 2", comunidad1, prestacion2);

    prestacion1.getIncidentes().add(incidente1);
    prestacion2.getIncidentes().add(incidente2);
  }

  @Test
  @DisplayName("Ranking: Entidades con mayor promedio de tiempo de cierre de incidentes")
  public void ranking1() throws SchedulerException, InterruptedException {
    incidente1.cerrar();
    Thread.sleep(1000);
    incidente2.cerrar();
    List<Entidad> entidades = new ArrayList<>();
    entidades.add(entidad1);
    entidades.add(entidad2);

    List<String> ranking1 = new ArrayList<>();
    ranking1.add(entidad2.getDenominacion());
    ranking1.add(entidad1.getDenominacion());

    Assertions.assertEquals(ranking1, Rankeador.obtenerInstancia().elaborarRankingPromedioCierre(entidades));
  }

  @Test
  @DisplayName("Ranking: Entidades con mayor cantidad de incidentes reportados en la semana")
  public void ranking2() throws SchedulerException, InterruptedException {

    Incidente incidente3 = new Incidente("observaciones 3", comunidad1, prestacion1);

    prestacion1.getIncidentes().add(incidente3);

    incidente1.cerrar();
    incidente2.cerrar();
    incidente3.cerrar();

    List<Entidad> entidades = new ArrayList<>();
    entidades.add(entidad1);
    entidades.add(entidad2);

    List<String> ranking2 = new ArrayList<>();
    ranking2.add(entidad1.getDenominacion());
    ranking2.add(entidad2.getDenominacion());

    Assertions.assertEquals(ranking2, Rankeador.obtenerInstancia().elaborarRankingCantidadIncidentesReportados(entidades));
  }
  @Test
  @DisplayName("Ranking: Mayor grado de impacto de las problemáticas")
  public void ranking3() throws SchedulerException, InterruptedException {
    Comunidad comunidad2 = new Comunidad();
    Usuario usuario = new Usuario("pepe", "argento");
    Persona persona1 = new Persona(usuario, "panchito@gmail.com", "1234", new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro1 = new Miembro(persona1, comunidad1);

    Persona persona2 = new Persona(usuario, "vivaLaPepa@gmail.com", "1234", new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro2 = new Miembro(persona2, comunidad1);

    Persona persona3 = new Persona(usuario, "yolo@gmail.com", "1234", new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro3 = new Miembro(persona3, comunidad2);

    //TODO
  }
}
