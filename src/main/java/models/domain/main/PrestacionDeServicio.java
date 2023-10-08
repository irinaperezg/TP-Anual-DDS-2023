package models.domain.main;

import models.domain.main.incidentes.Incidente;
import models.domain.main.notificaciones.Notificador;
import models.domain.main.notificaciones.frecuenciasNotificacion.Notificacion;
import models.domain.main.servicio.Servicio;
import domain.usuarios.*;
import lombok.Getter;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Miembro;
import models.domain.usuarios.Persona;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="prestacionDeServicio")
public class PrestacionDeServicio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @ManyToOne
  @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
  private Establecimiento establecimiento;

  @Getter
  @ManyToOne
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;

  @OneToMany(mappedBy = "prestacion", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Incidente> incidentes = new ArrayList<>();

  public PrestacionDeServicio() {
    this.establecimiento = null;
    this.servicio = null;
  }

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
    //TODO: ARREGLAR ESTO
    //List<Persona> listadoPersonasInteresadas = new ArrayList<>(incidente.getComunidad().getMiembros().stream().map(Miembro::getPersona).toList());
    List<Persona> listadoPersonasInteresadas = new ArrayList<>();
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