package domain.main;

import domain.localizacion.Localizacion;
import domain.main.entidades.Entidad;
import domain.usuarios.Miembro;
import domain.usuarios.Notificador;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

public class Establecimiento {
  private final String denominacion;
  private final Entidad entidad;
  @Setter
  private Localizacion localizacion = null;

  public Establecimiento(Entidad entidad, String denominacion) {
    this.entidad = entidad;
    this.denominacion = denominacion;
  }

  public void notificarInteresados(Servicio servicio, boolean esIncidente) {
    List<Miembro> interesados = new ArrayList<>(this.entidad.buscarInteresados(this.localizacion, servicio));
    new Notificador().notificarIncidenteOArreglo(esIncidente,interesados,servicio.getDescripcion(),this.entidad.getDenominacion(),this.denominacion,this.entidad.getTipo());
  }
}