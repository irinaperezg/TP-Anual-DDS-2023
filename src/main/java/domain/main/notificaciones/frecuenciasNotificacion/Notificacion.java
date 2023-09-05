package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.Establecimiento;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.Persona;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
public class Notificacion {
  private Persona destinatario;
  @Setter
  private String mensaje;
  private EstadoNotificacion estado;
  private LocalDateTime fechaAperturaIncidente;
  private LocalDateTime fechaCierreIncidente;

  public Notificacion (Persona destinatario, Incidente incidente) {
    this.destinatario = destinatario;
    this.mensaje = this.generarMensaje(incidente);
    estado = EstadoNotificacion.PENDIENTE_A_ENVIAR;
    fechaAperturaIncidente = incidente.getFechaApertura();
    fechaCierreIncidente = incidente.getFechaCierre();
  }

  public String generarMensaje(Incidente incidente) {
    Establecimiento establecimiento = incidente.getPrestacion().getEstablecimiento();
    Servicio servicio = incidente.getPrestacion().getServicio();
    if (incidente.isAbierto()) {
      return "Nuevo incidente en " + establecimiento.getDenominacion() + " en el servicio " + servicio.getDescripcion()
          + " abierto a las " + incidente.getFechaApertura().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    } else {
      return "Cierre de incidente en " + establecimiento.getDenominacion() + " en el servicio " + servicio.getDescripcion()
          + " cerrado a las " + incidente.getFechaCierre().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
  }

  public void cambiarEstadoAEnviado() {
    estado = EstadoNotificacion.ENVIADO;
  }
}