package domain.entidades;

import domain.localizacion.Localizacion;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Establecimiento {
  private String denominacion;
  private List<PrestacionDeServicio> prestacionesDeServicios = new ArrayList<>();
  @Setter
  private Localizacion localizacion = null;

  public Establecimiento(String denominacion) {
    this.denominacion = denominacion;
  }

  public void agregarPrestacionDeServicios(PrestacionDeServicio ... unasPrestaciones) {
    prestacionesDeServicios.addAll(Arrays.stream(unasPrestaciones).toList());
  }

  public boolean tuvoIncidenteEnUn(Servicio servicio){
    return prestacionesDeServicios.stream().anyMatch(prestacionServicio -> prestacionServicio.tieneServicio(servicio) && !prestacionServicio.isDisponible());
  }

  public boolean tuvoIncidente(List<Servicio> servicios) {
    return servicios.stream().anyMatch(servicio -> this.tuvoIncidenteEnUn(servicio));
  }

  public boolean estaCerca(Localizacion localizacionConsultada) {
    return localizacionConsultada.equals(localizacion);
  }
}