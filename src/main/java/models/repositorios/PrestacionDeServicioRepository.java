package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;

import java.util.List;

public class PrestacionDeServicioRepository implements WithSimplePersistenceUnit {
  public void registrar(PrestacionDeServicio prestacionDeServicio) {
    entityManager().persist(prestacionDeServicio);
  }

  public List<PrestacionDeServicio> todos() {
    return entityManager()
            .createQuery("from PrestacionDeServicio")
            .getResultList();

  }

  public PrestacionDeServicio buscarPorID(Long id) {
    return entityManager().find(PrestacionDeServicio.class, id);
  }
}
/*
  public List<PrestacionDeServicio> prestacionesDeUnServicio (Servicio servicio) {




}
*/