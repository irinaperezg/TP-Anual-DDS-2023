package models.repositorios;

import models.domain.main.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class EstablecimientoRepository implements WithSimplePersistenceUnit {

  public void registrar(Establecimiento establecimiento) {
    entityManager().persist(establecimiento);
  }

  public List<Establecimiento> todos() {
    return entityManager()
        .createQuery("from Establecimiento")
        .getResultList();
  }

  public Establecimiento buscarPorID(Long id) {
    return entityManager().find(Establecimiento.class, id);
  }

  public List<Establecimiento> obtenerEstablecimientosAsociados(Long comunidadId) {
    String jpql = "SELECT e FROM Establecimiento e " +
        "JOIN e.comunidades c " +
        "WHERE c.id = :comunidadId";

    return entityManager().createQuery(jpql, Establecimiento.class).setParameter("comunidadId", comunidadId).getResultList();
  }

}
