package models.repositorios;

import models.domain.main.Establecimiento;
import models.domain.main.entidades.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class EntidadRepository implements WithSimplePersistenceUnit {

  public void registrar(Entidad entidad) {
    entityManager().getTransaction().begin();
    entityManager().persist(entidad);
    entityManager().getTransaction().commit();
  }

  public List<Entidad> todos() {
    return entityManager()
        .createQuery("from Entidad")
        .getResultList();
  }

  public Entidad buscarPorID(Long id) {
    return entityManager().find(Entidad.class, id);
  }

  public void remove(Entidad entidad) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    Entidad managedEntidad = entityManager().merge(entidad);
    entityManager().remove(managedEntidad);
    tx.commit();
  }


}
