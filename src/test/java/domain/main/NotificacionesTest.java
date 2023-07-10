package domain.main;
import domain.main.entidades.Entidad;
import domain.main.entidades.TipoEntidad;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.frecuenciasNotificacion.Calendario;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionSinApuros;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioBase;
import domain.usuarios.Comunidad;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.quartz.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
class NotificacionesTest {
  Notificador notificador = Notificador.obtenerInstancia();

  @Test
  void notificarIncidentes() throws SchedulerException, InterruptedException {
    NotificacionSinApuros notificacionSinApuros = new NotificacionSinApuros();
    Comunidad comunidad = new Comunidad();
    Usuario usuario = new Usuario("pepe", "argento");
    List<LocalDateTime> listaHorarios = new ArrayList<>();

    LocalDateTime horarioEspecifico1 = LocalDateTime.of(2023, Month.JULY, 10, 13, 15);
    LocalDateTime horarioEspecifico2 = LocalDateTime.of(2023, Month.JULY, 10, 13, 16);

    listaHorarios.add(horarioEspecifico1);
    listaHorarios.add(horarioEspecifico2);
    Persona persona1 = new Persona(usuario, "ej1@gmail.com", "1234", notificacionSinApuros, PreferenciaMedioNotificacion.EMAIL, listaHorarios);
    Persona persona2 = new Persona(usuario, "ej2@gmail.com", "1234", notificacionSinApuros, PreferenciaMedioNotificacion.EMAIL, listaHorarios);
    Servicio servicio = new ServicioBase("baño sin genero");
    TipoEntidad tipoEntidad = new TipoEntidad();
    Entidad entidad = new Entidad(tipoEntidad, "entidad");
    Establecimiento establecimiento1 = new Establecimiento(entidad, "Banco Nacion");
    PrestacionDeServicio prestacion = new PrestacionDeServicio(establecimiento1, servicio);

    LocalDateTime now = LocalDateTime.now();
    String observaciones = "";
    String denominacion = " ";
    Incidente incidente1 = new Incidente(observaciones, denominacion, comunidad, prestacion);
    incidente1.setFechaApertura(now.minusHours(23)); // menos de 24 hs

    Incidente incidente2 = new Incidente(observaciones, denominacion, comunidad, prestacion);
    incidente2.setFechaApertura(now.minusHours(23)); // mas de 24 hs

    Incidente incidente3 = new Incidente(observaciones, denominacion, comunidad, prestacion);
    incidente3.setFechaApertura(now.minusHours(25)); // entre las 24 hs

    notificacionSinApuros.gestionarInicidente(persona1, incidente1);
    notificacionSinApuros.gestionarInicidente(persona2, incidente3);

    System.out.println("Esperando mensajes");

    Thread.sleep(60000);
    notificacionSinApuros.gestionarInicidente(persona1, incidente2);
    Thread.sleep(100000);
  }
}
