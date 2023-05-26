package domain.establecimientos;

public class PrestacionDeServicio {
  private Establecimiento establecimiento;
  private Servicio servicio;

  public boolean estaDisponible() {

    return true;
  }

  public boolean tieneServicio(Servicio servicioConsultado) {
    return servicioConsultado.equals(servicio);
  }
}