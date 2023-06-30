package domain.main;

import domain.main.incidentes.Incidente;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrestacionDeServicio {
  private final Establecimiento establecimiento;
  private final Servicio servicio;
  private final List<Incidente> incidentes = new ArrayList<>();

  public PrestacionDeServicio(Establecimiento establecimiento, Servicio servicio) {
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }

  public void ocurrioUnIncidente(Miembro informante, String descripcion) {
    for(Comunidad comunidad : informante.getComunidades()) {
        crearIncidente(descripcion, comunidad, informante);
        //avisar a los miembros de la comunidad
    }

    //avisar a los miembros individuales interesados
    //this.establecimiento.notificarInteresados(this.servicio, true);
  }

  private void crearIncidente(String descripcion, Comunidad comunidad, Miembro informante) {
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    Incidente incidente = new Incidente(fechaHoraActual, descripcion, comunidad, this, informante);
    incidentes.add(incidente);

    comunidad.agregarIncidente(incidente);
  }

  public void cerrarUnIncidente(Miembro informante, Incidente incidente) {
    //cerrar incidente

    this.establecimiento.notificarInteresados(this.servicio, false);
  }

  public boolean disponibleParaComunidad(Comunidad comunidad) {
    return incidentes.stream().noneMatch(incidente -> incidente.getComunidad().equals(comunidad) && incidente.isAbierto());
  }
}
