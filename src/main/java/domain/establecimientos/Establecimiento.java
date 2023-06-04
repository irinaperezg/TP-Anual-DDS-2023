package domain.establecimientos;

import java.util.List;

public class Establecimiento {
  private String nombre;
  private List<PrestacionDeServicio> prestacionesDeServicios;
  private Localizacion localizacion;

  public boolean tuvoIncidenteEnUn(Servicio servicio){
    return prestacionesDeServicios.stream().anyMatch(prestacionServicio -> prestacionServicio.tieneServicio(servicio) && !prestacionServicio.estaDisponible());
  }

  public boolean tuvoIncidente(List<Servicio> servicios) {
    return servicios.stream().anyMatch(servicio -> this.tuvoIncidenteEnUn(servicio));
  }

  public boolean estaCerca(Localizacion localizacionConsultada) {
    return localizacionConsultada.equals(localizacion);
  }
}