package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.usuarios.Persona;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.time.LocalDateTime;

public class Calendario {
  public Calendario(Persona persona) throws SchedulerException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();

    int contadorHorarios = 0;
    for (LocalDateTime horario : persona.getHorariosDeNotificaciones()) {
      String nombreGrupo = persona.getEmail() + contadorHorarios;
      JobDetail job = JobBuilder.newJob(NotificacionSinApuros.class)
          .withIdentity("miNotificacion", nombreGrupo)
          .build();

      JobDataMap jobDataMap = job.getJobDataMap();
      jobDataMap.put("persona", persona);

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity("myTrigger", nombreGrupo)
          .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horario.getHour(), horario.getMinute()))
          .build();

      contadorHorarios = contadorHorarios + 1 ;

      // Schedule the job with the trigger
      scheduler.scheduleJob(job, trigger);
    }
  }

  public static void borrarJobs() {
    try {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();

      for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyJobGroup())) {
        scheduler.deleteJob(jobKey);
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}