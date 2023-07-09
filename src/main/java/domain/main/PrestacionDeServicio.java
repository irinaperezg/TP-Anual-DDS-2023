package domain.main;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.servicio.Servicio;
import domain.usuarios.*;

import java.util.*;
import java.util.stream.Collectors;

public class PrestacionDeServicio {
  private final Establecimiento establecimiento;
  private final Servicio servicio;

  private final List<Incidente> incidentes = new ArrayList<>();
  public List<Incidente> getIncidentes() {
    return incidentes;
  }

  public PrestacionDeServicio(Establecimiento establecimiento, Servicio servicio) {
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }


  public void ocurrioUnIncidente(Miembro miembro, String descripcion) {
    List<Persona> listadoPersonasInteresadas = new ArrayList<>();
    Comunidad comunidadMiembro = miembro.getComunidad();
    Incidente incidente = new Incidente(descripcion, comunidadMiembro, this);
    incidentes.add(incidente);
    comunidadMiembro.agregarIncidente(incidente);

    listadoPersonasInteresadas.addAll(comunidadMiembro.getMiembros().stream().map(Miembro::getPersona).toList());
    listadoPersonasInteresadas.addAll(buscarInteresados());
    listadoPersonasInteresadas.stream().collect(Collectors.toSet()).stream().toList();

    new Notificador().notificarAperturaIncidente(listadoPersonasInteresadas, incidente);
  }

  private List<Persona> buscarInteresados() {
    return establecimiento.buscarInteresados(this.servicio);
  }

  public void cerrarUnIncidente(Incidente incidente) {
    incidente.cerrar();
    List<Persona> listadoPersonasInteresadas = new ArrayList<>(incidente.getComunidad().getMiembros().stream().map(miembro -> miembro.getPersona()).toList());
    listadoPersonasInteresadas.addAll(buscarInteresados());
    listadoPersonasInteresadas.stream().collect(Collectors.toSet()).stream().toList();
    new Notificador().notificarCierreIncidente(listadoPersonasInteresadas, incidente);
  }

  public boolean disponibleParaComunidad(Comunidad comunidad) {
    return incidentes.stream().noneMatch(incidente -> incidente.getComunidad().equals(comunidad) && incidente.isAbierto());
  }
}
