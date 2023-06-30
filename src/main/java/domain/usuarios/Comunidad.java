package domain.usuarios;

import domain.main.Servicio;
import domain.main.incidentes.Incidente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comunidad {
  private String descripcion;
  private List<Miembro> observadores = new ArrayList<>();
  private List<Miembro> afectados= new ArrayList<>();
  private List<Miembro> administradores= new ArrayList<>();
  private List<Incidente> incidentesActivos= new ArrayList<>();
  private List<Servicio> serviciosDeInteres= new ArrayList<>();

  public void agregarServicioDeInteres(Servicio servicio) {
    serviciosDeInteres.add(servicio);
  }

  public boolean estaServicioDeInteres(Servicio servicio) {
    return serviciosDeInteres.contains(servicio);
  }

  public void agregarIncidente(Incidente incidente) {
    incidentesActivos.add(incidente);
  }
}
