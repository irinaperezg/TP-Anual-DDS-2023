package models.repositorios;

import models.domain.usuarios.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Persona;

import javax.persistence.*;
import java.util.List;

public class MiembroRepository implements WithSimplePersistenceUnit {

  public void registrar(Miembro miembro)
  {
    entityManager().persist(miembro);
  }

  public List<Miembro> todos() {
    return entityManager()
        .createQuery("from Miembro")
        .getResultList();
  }

  public Miembro buscarPorID(Long id) {
    return entityManager().find(Miembro.class, id);
  }

  public Miembro buscarMiembroPorPersonaId(Long id) {
    return entityManager().createQuery(
              "SELECT m FROM Miembro m WHERE m.persona.id = :personaId",
              Miembro.class)
          .setParameter("personaId", id)
          .getSingleResult();

  }

  public void removeMiembro(Miembro miembro) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    Miembro managedMiembro = entityManager().merge(miembro);
    entityManager().remove(managedMiembro);
    tx.commit();
  }
}
