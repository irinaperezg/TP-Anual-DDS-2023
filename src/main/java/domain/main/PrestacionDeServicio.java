package domain.main;

import lombok.Getter;
import lombok.Setter;

public class PrestacionDeServicio {
  private final Establecimiento establecimiento;
  private final Servicio servicio;
  @Getter
  private boolean disponible = true;

  public PrestacionDeServicio(Establecimiento establecimiento, Servicio servicio) {
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }

  public void ocurrioUnIncidente() {
    this.disponible = false;
    this.establecimiento.notificarInteresados(this.servicio, true);
  }

  public void seArregloElServicio() {
    this.disponible = true;
    this.establecimiento.notificarInteresados(this.servicio, false);
  }
}
