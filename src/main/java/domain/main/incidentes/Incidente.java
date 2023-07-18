package domain.main.incidentes;

import domain.main.Establecimiento;
import domain.main.PrestacionDeServicio;
import domain.main.entidades.Entidad;
import domain.main.servicio.Servicio;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

@Setter @Getter
public class Incidente {
  private String observaciones;
  private String denominacion;
  private PrestacionDeServicio prestacion;
  private LocalDateTime fechaApertura;
  private LocalDateTime fechaCierre;
  private Comunidad comunidad;
  private boolean abierto;
  private Miembro creador;

  public Incidente(String observaciones, String denominacion, Comunidad comunidad, PrestacionDeServicio prestacion, Miembro creador) {
    this.fechaApertura = LocalDateTime.now();
    this.denominacion = denominacion;
    this.observaciones = observaciones;
    this.comunidad = comunidad;
    this.fechaCierre = null;
    this.abierto = true;
    this.prestacion = prestacion;
    this.creador = creador;
  }

  public void cerrar() {
    setFechaCierre(LocalDateTime.now());
    setAbierto(false);
  }

  public Duration calcularTiempoCierre() {
    LocalDateTime fechaApertura = this.getFechaApertura();
    LocalDateTime fechaCierre = this.getFechaCierre();
    return Duration.between(fechaApertura, fechaCierre);
  }

  public boolean perteneceSemanaActual() {
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    LocalDate inicioSemanaActual = fechaHoraActual.toLocalDate()
        .with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);

    LocalDateTime inicioSemanaActualDateTime = inicioSemanaActual.atStartOfDay();

    LocalDateTime finSemanaActualDateTime = inicioSemanaActualDateTime.plusDays(6)
        .withHour(23).withMinute(59).withSecond(59);

    return !fechaApertura.isBefore(inicioSemanaActualDateTime) && !fechaApertura.isAfter(finSemanaActualDateTime);
}

  public boolean esReciente(){
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    LocalDateTime fechaHoraApertura = this.getFechaApertura();
    long horasDiferencia = ChronoUnit.HOURS.between(fechaHoraApertura, fechaHoraActual);
    return horasDiferencia <= 24;
  }

  public int calcularImpactoSobreComunidad()
  {
    int impacto = this.getComunidad().getMiembros().size();
    return impacto;
  }

  // MÉTODOS PARA PODER COMPARAR INCIDENTES:
  // Dos incidentes son iguales si tienen misma denominación y si pertenecen a la misma comunidad.
  // Si hace falta más cosas para que sean iguales diganme y lo agrego.
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Incidente incidente = (Incidente) o;
    return Objects.equals(denominacion, incidente.getDenominacion()) &&
        Objects.equals(comunidad, incidente.getComunidad());
  }

  @Override
  public int hashCode() {
    return Objects.hash(denominacion, comunidad);
  }

}
