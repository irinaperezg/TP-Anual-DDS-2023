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
  void notificarIncidentes() throws SchedulerException {
    NotificacionSinApuros notificacionSinApuros = (mock(NotificacionSinApuros.class));
    Comunidad comunidad = new Comunidad();
    Usuario usuario = new Usuario("pepe", "argento");
    List<LocalDateTime> listaHorarios = new ArrayList<>();

    LocalDateTime horarioEspecifico1 = LocalDateTime.of(2023, Month.JULY, 10, 1, 40);
    LocalDateTime horarioEspecifico2 = LocalDateTime.of(2023, Month.JULY, 10, 1, 41);

    listaHorarios.add(horarioEspecifico1);
    listaHorarios.add(horarioEspecifico2);
    Persona persona1 = new Persona(usuario, "ej1@gmail.com", "1234", new NotificacionSinApuros(), PreferenciaMedioNotificacion.WHATSAPP, listaHorarios);
    Persona persona2 = new Persona(usuario, "ej2@gmail.com", "1234", new NotificacionSinApuros(), PreferenciaMedioNotificacion.WHATSAPP, listaHorarios);
    Servicio servicio = new ServicioBase("baño sin genero");
    TipoEntidad tipoEntidad = new TipoEntidad();
    Entidad entidad = new Entidad(tipoEntidad, "entidad");
    Establecimiento establecimiento1 = new Establecimiento(entidad, "Banco Nacion");
    PrestacionDeServicio prestacion = new PrestacionDeServicio(establecimiento1, servicio);

    LocalDateTime now = LocalDateTime.now();

    String observaciones = "";
    Incidente incidente1 = new Incidente(observaciones, comunidad, prestacion);
    incidente1.setFechaApertura(now.minusHours(23)); // menos de 24 hs

    Incidente incidente2 = new Incidente(observaciones, comunidad, prestacion);
    incidente2.setFechaApertura(now.minusHours(25)); // mas de 24 hs

    Incidente incidente3 = new Incidente(observaciones, comunidad, prestacion);
    incidente3.setFechaApertura(now.minusHours(23)); // entre las 24 hs

    notificacionSinApuros.gestionarInicidente(persona1, incidente1);
    notificacionSinApuros.gestionarInicidente(persona1, incidente2);
    notificacionSinApuros.gestionarInicidente(persona2, incidente3);

    for (LocalDateTime horario : listaHorarios) {
      JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("persona", persona1);
      when(jobExecutionContext.getJobDetail().getJobDataMap()).thenReturn(jobDataMap);
      when(jobExecutionContext.getJobDetail().getKey()).thenReturn(new JobKey("miNotificacion", persona1.getEmail()));
      when(jobExecutionContext.getTrigger().getKey()).thenReturn(new TriggerKey("myTrigger", persona1.getEmail()));
      when(jobExecutionContext.getFireTime()).thenReturn(Date.from(horario.atZone(ZoneId.systemDefault()).toInstant()));
      notificacionSinApuros.execute(jobExecutionContext);
    }

    // Verify that the method removed the pairs with persona1
    assertFalse(notificacionSinApuros.getListaPares().stream().anyMatch(pair -> pair.getRight().equals(persona1)));

    // Verify that the method was called at the specified times for persona1
    verify(notificacionSinApuros, times(listaHorarios.size())).notificarIncidentes(persona1);

    // Verify that the method was not called for persona2
    verify(notificacionSinApuros, never()).notificarIncidentes(persona2);

    // notificacionSinApuros.notificarIncidentes(persona1);

// Verify that the method removed the pairs with persona1
    /*assertFalse(notificacionSinApuros.getListaPares().stream().anyMatch(pair -> pair.getRight().equals(persona1)));

    //NotificacionSinApuros notificacionSinApuros = (mock(NotificacionSinApuros.class));

    doNothing().when(notificacionSinApuros).notificarIncidentes(persona1);
    doNothing().when(notificacionSinApuros).notificarIncidentes(persona2);
    // Perform assertions based on the expected behavior
    for (Pair<Incidente, Persona> pair : notificacionSinApuros.getListaPares()) {
      Incidente incidente = pair.getLeft();
      Persona persona = pair.getRight();

      if (persona.equals(persona1)) {

        if (incidente.equals(incidente1)) {
          verify(notificacionSinApuros, times(1)).notificarIncidentes(persona);
        }

        if (incidente.equals(incidente2)) {
          verify(notificacionSinApuros, times(1)).notificarIncidentes(persona);
        }
      }

      if (persona.equals(persona2)) {
        verify(notificacionSinApuros, times(1)).notificarIncidentes(persona);
      }
    }*/
  }
}