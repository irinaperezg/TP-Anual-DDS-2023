package domain.main;

import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.usuarios.*;

import java.util.*;
import java.util.stream.Collectors;

public class PrestacionDeServicio {
  private final Establecimiento establecimiento;
  private final Servicio servicio;
  private final List<Incidente> incidentes = new ArrayList<>();

  public PrestacionDeServicio(Establecimiento establecimiento, Servicio servicio) {
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }


  public void ocurrioUnIncidente(Persona informante, String descripcion) {
    List<Persona> listadoPersonasInteresadas = new ArrayList<>();
    for(Comunidad comunidad : informante.getComunidades()) {
      crearIncidente(descripcion, comunidad);
      listadoPersonasInteresadas.addAll(comunidad.getMiembros().stream().map(Miembro::getPersona).toList());
    }
    listadoPersonasInteresadas.addAll(buscarInteresados());
    listadoPersonasInteresadas.stream().collect(Collectors.toSet()).stream().toList();
    new Notificador().notificarIncidente(listadoPersonasInteresadas);

  }

  private List<Persona> buscarInteresados() {
    return establecimiento.buscarInteresados(this.servicio);
  }

  private void crearIncidente(String observaciones, Comunidad comunidad) {
    Incidente incidente = new Incidente(observaciones, comunidad, this);
    incidentes.add(incidente);
    comunidad.agregarIncidente(incidente);
  }

  public void cerrarUnIncidente(Incidente incidente) {
    incidente.cerrar();
    List<Persona> listadoPersonasInteresadas = new ArrayList<>(incidente.getComunidad().getMiembros().stream().map(miembro -> miembro.getPersona()).toList());
    listadoPersonasInteresadas.addAll(buscarInteresados());
    listadoPersonasInteresadas.stream().collect(Collectors.toSet()).stream().toList();
    new Notificador().notificarCierreIncidente(listadoPersonasInteresadas);
  }

  public boolean disponibleParaComunidad(Comunidad comunidad) {
    return incidentes.stream().noneMatch(incidente -> incidente.getComunidad().equals(comunidad) && incidente.isAbierto());
  }
}
