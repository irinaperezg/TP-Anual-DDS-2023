package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.entidades.Entidad;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;

import javax.persistence.EntityTransaction;
import java.util.List;

public class PrestacionDeServicioRepository implements WithSimplePersistenceUnit {
  public void registrar(PrestacionDeServicio prestacionDeServicio) {
    entityManager().getTransaction().begin();
    entityManager().persist(prestacionDeServicio);
    entityManager().getTransaction().commit();
  }

  public List<PrestacionDeServicio> todos() {
    return entityManager()
            .createQuery("from PrestacionDeServicio")
            .getResultList();

  }

  public PrestacionDeServicio buscarPorID(Long id) {
    return entityManager().find(PrestacionDeServicio.class, id);
  }


  public void remove(PrestacionDeServicio prestacionDeServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    PrestacionDeServicio managedPrestacionDeServicio = entityManager().merge(prestacionDeServicio);
    entityManager().remove(managedPrestacionDeServicio);
    tx.commit();
  }
}



/*
  public List<PrestacionDeServicio> prestacionesDeUnServicio (Servicio servicio) {




}
*/