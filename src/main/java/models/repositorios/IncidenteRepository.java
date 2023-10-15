package models.repositorios;

import models.domain.main.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Comunidad;

import java.util.List;

public class IncidenteRepository implements WithSimplePersistenceUnit {

  public void registrar(Incidente incidente)
  {
    entityManager().persist(incidente);
  }

  public List<Incidente> todos() {
    return entityManager()
        .createQuery("from Incidente")
        .getResultList();
  }

  public Incidente buscarPorID(Long id) {
    return entityManager().find(Incidente.class, id);
  }

  public List<Incidente> incidentesDeComunidadSegunEstado (Comunidad comunidad, boolean abierto) {
    String queryIncidentes = "SELECT * FROM Incidente I " +
        "WHERE I.comunidad_id = " +
        comunidad.getId() +
        "AND I.abierto =  " +
        abierto;

    return entityManager().createQuery(queryIncidentes).getResultList();
  }
}
