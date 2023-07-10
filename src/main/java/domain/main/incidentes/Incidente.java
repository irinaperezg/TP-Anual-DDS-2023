package domain.main.incidentes;

import domain.main.Establecimiento;
import domain.main.PrestacionDeServicio;
import domain.main.servicio.Servicio;
import domain.usuarios.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;
@Setter @Getter
public class Incidente {
  private String observaciones;
  private PrestacionDeServicio prestacion;
  private LocalDateTime fechaApertura;
  private LocalDateTime fechaCierre;
  private Comunidad comunidad;
  private boolean abierto;

  public Incidente(String observaciones, Comunidad comunidad, PrestacionDeServicio prestacion) {
    this.fechaApertura = LocalDateTime.now();
    this.observaciones = observaciones;
    this.comunidad = comunidad;
    this.fechaCierre = null;
    this.abierto = true;
    this.prestacion = prestacion;
  }

  public void cerrar(){
    setFechaCierre(LocalDateTime.now());
    setAbierto(false);
  }

  public Duration calcularTiempoCierre() {
    LocalDateTime fechaApertura = this.getFechaApertura();
    LocalDateTime fechaCierre = this.getFechaCierre();
    return Duration.between(fechaApertura, fechaCierre);
  }

  public boolean perteneceSemanaActual() {
    // Obtener la fecha y hora actual
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Obtener la fecha de inicio de la semana actual
    LocalDate inicioSemanaActual = fechaHoraActual.toLocalDate()
        .with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);

    LocalDateTime inicioSemanaActualDateTime = inicioSemanaActual.atStartOfDay();

    // Obtener la fecha de fin de la semana actual
    LocalDateTime finSemanaActualDateTime = inicioSemanaActualDateTime.plusDays(6)
        .withHour(23).withMinute(59).withSecond(59);

    LocalDateTime fecha = this.getFechaApertura();

    // Verificar si la fecha de apertura del incidente está dentro de la semana actual
    return !fechaApertura.isBefore(inicioSemanaActualDateTime) && !fechaApertura.isAfter(finSemanaActualDateTime);
}

  public boolean esReciente() {
    // Obtener la fecha y hora actual
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Obtener la fecha y hora de creación del incidente
    LocalDateTime fechaHoraApertura = this.getFechaApertura();

    // Obtener la diferencia en horas entre la fecha de creación y la fecha actual
    long horasDiferencia = ChronoUnit.HOURS.between(fechaHoraApertura, fechaHoraActual);

    // Verificar si la diferencia está dentro de las últimas 24 horas
    return horasDiferencia <= 24;
  }

  public int calcularImpactoSobreComunidad()
  {
    int impacto = this.getComunidad().getMiembros().size();
    return impacto;
  }

  public String generarMensaje() {
    Establecimiento establecimiento = this.prestacion.getEstablecimiento();
    Servicio servicio = this.prestacion.getServicio();
    if (this.isAbierto()) {
      return "Nuevo incidente en " + establecimiento.getDenominacion() + " en el servicio " + servicio.getDescripcion()
          + " abierto a las " + this.fechaApertura.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    } else {
      return "Cierre de incidente en " + establecimiento.getDenominacion() + " en el servicio " + servicio.getDescripcion()
          + " cerrado a las " + this.fechaCierre.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
  }
}
