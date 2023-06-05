package domain.entidades;

import domain.localizacion.Localizacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entidad {
  private String denominacion;
  private List<Establecimiento> establecimientos = new ArrayList<>();

  public Entidad(String denominacion) {
    this.denominacion = denominacion;
  }

  public void agregarEstablecimientos(Establecimiento ... unosEstablecimientos) {
    establecimientos.addAll(Arrays.stream(unosEstablecimientos).toList());
  }

  public boolean tuvoIncidente (List<Servicio> servicios){
    return establecimientos.stream().anyMatch(establecimiento -> establecimiento.tuvoIncidente(servicios));
  }

  public boolean estaCerca(Localizacion localizacionConsultada) {
      return establecimientos.stream().anyMatch(establecimiento -> establecimiento.estaCerca(localizacionConsultada));
  }
}