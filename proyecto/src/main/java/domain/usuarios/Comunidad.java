package domain.usuarios;

import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioCompuesto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {
  private String descripcion;
  @Getter
  private List<Miembro> miembros = new ArrayList<>();
  private List<Miembro> administradores= new ArrayList<>();
  private List<Incidente> incidentes= new ArrayList<>();

  public void agregarIncidente(Incidente incidente) {
    incidentes.add(incidente);
  }
  public ServicioCompuesto crearServicioCompuesto(String descripcion, Servicio... otrosServicios){
    return new ServicioCompuesto(descripcion, otrosServicios);
  }

  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }
}
