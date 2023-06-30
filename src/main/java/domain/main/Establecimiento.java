package domain.main;

import domain.localizacion.main.Localidad;
import domain.localizacion.main.Localizacion;
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
  private Localidad localidad = null;
  private final List<PrestacionDeServicio> prestaciones = new ArrayList<>();

  public Establecimiento(Entidad entidad, String denominacion) {
    this.entidad = entidad;
    this.denominacion = denominacion;
  }

  public void notificarInteresados(Servicio servicio, boolean esIncidente) {
    List<Miembro> interesados = new ArrayList<>(this.entidad.buscarInteresados(this.localidad, servicio));
    new Notificador().notificarIncidenteOArreglo(esIncidente,interesados,servicio.getDescripcion(),this.entidad.getDenominacion(),this.denominacion,this.entidad.getTipo());
  }
}