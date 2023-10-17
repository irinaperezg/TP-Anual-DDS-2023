package models.repositorios;

import models.domain.main.Establecimiento;
import models.domain.main.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

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

  //TODO: buscarIncidentesDeTodasLasComunidades
  //public List<Incidente> buscarIncidentesDeTodasLasComunidades (Long comunidadId)

  public List<Incidente> buscarPorComunidad (Long comunidadId) {
    String jpqlQuery = "SELECT i FROM Incidente i " +
            "WHERE i.comunidad.id = :comunidadId";

    return entityManager()
            .createQuery(jpqlQuery, Incidente.class)
            .setParameter("comunidadId", comunidadId)
            .getResultList();
  }

  public List<Incidente> buscarPorComunidadYEstado (Long comunidadId, Boolean abierto) {
    String jpqlQuery = "SELECT i FROM Incidente i " +
            "WHERE i.comunidad.id = :comunidadId " +
            "AND i.abierto = :abierto";

    return entityManager()
            .createQuery(jpqlQuery, Incidente.class)
            .setParameter("comunidadId", comunidadId)
            .setParameter("abierto", abierto)
            .getResultList();
  }

  /*public List<Incidente> obtenerIncidentesAsociados(Long entidadId) {
    String jpql = "SELECT i FROM Incidente i " +
        "JOIN i.comunidades c " +
        "WHERE c.id = :comunidadId";

    return entityManager().createQuery(jpql, Establecimiento.class).setParameter("comunidadId", comunidadId).getResultList();
  } */
}
