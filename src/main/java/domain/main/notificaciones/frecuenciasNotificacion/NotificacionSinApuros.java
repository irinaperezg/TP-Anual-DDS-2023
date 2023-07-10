package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacionSinApuros implements FrecuenciaNotificacion, Job {
  @Getter @Setter
  static List<Pair<Incidente, Persona>> listaPares = new ArrayList<>();

  @Override
  public void gestionarInicidente(Persona persona, Incidente incidente) {
    Pair<Incidente, Persona> par = Pair.of(incidente, persona);
    listaPares.add(par);
  }
  private Boolean dentroDeLasUltimas24Horas(Incidente incidente) {
    LocalDateTime fechaCierre = incidente.getFechaCierre();
    if(fechaCierre != null) {
      return fechaCierre.isAfter(LocalDateTime.now().minusHours(24));
    }
    LocalDateTime fechaApertura = incidente.getFechaApertura();
    return fechaApertura.isAfter(LocalDateTime.now().minusHours(24));
  }

  public void notificarIncidentes(Persona personaANotificar) {
    List<Pair<Incidente, Persona>> paresDeIncidentes = listaPares.stream().filter(par -> par.getRight().getEmail().equals(personaANotificar.getEmail())).toList();
    List<Incidente> incidentes = paresDeIncidentes.stream().map(Pair::getLeft).toList();
    incidentes.forEach(incidente -> {
      if (dentroDeLasUltimas24Horas(incidente)) {
        String mensaje = incidente.generarMensaje();
        Notificador.obtenerInstancia().enviarNotificacion(personaANotificar, mensaje); // porque es un Singleton
      }
    });
    listaPares.removeAll(paresDeIncidentes);
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    Persona persona = (Persona) jobDataMap.get("persona");
    this.notificarIncidentes(persona);
  }
}