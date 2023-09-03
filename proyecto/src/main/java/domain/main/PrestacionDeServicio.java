package domain.main;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.frecuenciasNotificacion.Notificacion;
import domain.main.servicio.Servicio;
import domain.usuarios.*;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class PrestacionDeServicio {
  @Getter
  private final Establecimiento establecimiento;
  @Getter
  private final Servicio servicio;

  private final List<Incidente> incidentes = new ArrayList<>();
  public List<Incidente> getIncidentes() {
    return incidentes;
  }

  public PrestacionDeServicio(Establecimiento establecimiento, Servicio servicio) {
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }

  private List<Persona> buscarInteresados() {
    return establecimiento.buscarInteresados(this.servicio);
  }

  public void notificarInteresados (Incidente incidente) {
    List<Persona> listadoPersonasInteresadas = new ArrayList<>(incidente.getComunidad().getMiembros().stream().map(Miembro::getPersona).toList());
    listadoPersonasInteresadas.addAll(buscarInteresados());
    listadoPersonasInteresadas.stream().collect(Collectors.toSet()).stream().toList();
    for (Persona persona : listadoPersonasInteresadas){
      persona.getFrecuenciaNotification().gestionarInicidente(persona, incidente);
    }
  }

  public void ocurrioUnIncidente(Miembro miembro, String descripcion, String denominacion) {
    Comunidad comunidadMiembro = miembro.getComunidad();
    Incidente incidente = new Incidente(descripcion, denominacion, comunidadMiembro, this, miembro);
    incidentes.add(incidente);
    comunidadMiembro.agregarIncidente(incidente);
    notificarInteresados(incidente);
  }

  public void cerrarUnIncidente(Incidente incidente) {
    incidente.cerrar();
    notificarInteresados(incidente);
  }

  public void solicitarRevisionDeIncidenteA(Persona persona, Incidente incidente) {
    Notificacion notificacion = new Notificacion(persona, incidente);
    notificacion.setMensaje("Por favor, revise el estado del servicio " + servicio.getDescripcion() + " en " + establecimiento.getDenominacion());
    Notificador.obtenerInstancia().enviarNotificacion(notificacion);
  }

  public boolean disponibleParaComunidad(Comunidad comunidad) {
    return incidentes.stream().noneMatch(incidente -> incidente.getComunidad().equals(comunidad) && incidente.isAbierto());
  }
}
