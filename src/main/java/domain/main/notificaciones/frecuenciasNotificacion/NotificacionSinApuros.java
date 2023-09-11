package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacionSinApuros implements Job, FrecuenciaNotificacion {

  @Getter @Setter
  static List<Notificacion> notificaciones = new ArrayList<>();

  public void gestionarInicidente(Persona persona, Incidente incidente) {
    Notificacion notificacion = new Notificacion(persona, incidente);
    notificaciones.add(notificacion);
  }

  private Boolean dentroDeLasUltimas24Horas(Notificacion notificacion) {
    LocalDateTime fechaCierre = notificacion.getFechaCierreIncidente();
    if(fechaCierre != null) {
      return fechaCierre.isAfter(LocalDateTime.now().minusHours(24));
    }
    LocalDateTime fechaApertura = notificacion.getFechaAperturaIncidente();
    return fechaApertura.isAfter(LocalDateTime.now().minusHours(24));
  }

  public void notificarIncidentes(Persona personaANotificar) {
    List<Notificacion> notisDePersona = notificaciones.stream().filter(notificacion -> (notificacion.getDestinatario()).equals(personaANotificar)).toList();
    notisDePersona.forEach(notificacion -> {
      if (dentroDeLasUltimas24Horas(notificacion)) {
        Notificador.obtenerInstancia().enviarNotificacion(notificacion); // porque es un Singleton
      }
    });
    notificaciones.removeAll(notisDePersona);
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    Persona persona = (Persona) jobDataMap.get("persona");
    this.notificarIncidentes(persona);
  }

}