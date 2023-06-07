package domain.entidades;

import lombok.Getter;
import lombok.Setter;

public class PrestacionDeServicio {
  private Establecimiento establecimiento;
  private Servicio servicio;
  @Setter @Getter
  private boolean disponible = true;

  public PrestacionDeServicio(Servicio servicio) {
    this.servicio = servicio;
  }

  public boolean tieneServicio(Servicio servicioConsultado) {
    return servicioConsultado.equals(servicio);
  }
}
