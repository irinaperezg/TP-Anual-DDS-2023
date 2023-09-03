package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.notificaciones.Notificador;
import domain.usuarios.Persona;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.time.LocalDateTime;

public class Calendario {
  public static void iniciarCalendar() throws SchedulerException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();

    JobDetail job = JobBuilder.newJob(NotificacionSinApuros.class)
        .withIdentity("miNotificacion", "group")
        .build();

    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("myTrigger", "group")
        .withSchedule(CronScheduleBuilder.cronSchedule("*/15 * * * *"))
        .build();

    // Schedule the job with the trigger
    scheduler.scheduleJob(job, trigger);
  }

  public static void borrarJob() {
    try {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      scheduler.deleteJob(new JobKey("miNotificacion"));
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}