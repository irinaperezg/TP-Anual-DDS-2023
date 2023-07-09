package domain.main.incidentes;

import domain.main.PrestacionDeServicio;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

public class Incidente {
  private String observaciones;
  private PrestacionDeServicio prestacion;
  private LocalDateTime fechaApertura;
  @Setter
  private LocalDateTime fechaCierre;
  @Getter
  private Comunidad comunidad;
  @Setter @Getter
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

  public String getObservaciones() {
    return observaciones;
  }

  public LocalDateTime getFechaApertura() {
    return fechaApertura;
  }

  public LocalDateTime getFechaCierre() {
    return fechaCierre;
  }

  public Duration calcularTiempoCierre() {
    LocalDateTime fechaApertura = this.getFechaApertura();
    LocalDateTime fechaCierre = this.getFechaCierre();
    return Duration.between(fechaApertura, fechaCierre);
  }

  public boolean perteneceSemanaActual(LocalDateTime fecha) {
    // Obtener la fecha y hora actual
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Obtener la fecha de inicio de la semana actual
    LocalDate inicioSemanaActual = fechaHoraActual.toLocalDate()
        .with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);

    // Obtener la fecha de fin de la semana actual
    LocalDate finSemanaActual = inicioSemanaActual.plusDays(6);

    // Verificar si la fecha proporcionada está dentro de la semana actual
    return !fecha.isBefore(inicioSemanaActual.atStartOfDay()) && !fecha.isAfter(finSemanaActual.atTime(23,59,59));
}


}
