package models.repositorios;

import models.domain.main.Establecimiento;
import models.domain.main.entidades.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class EntidadRepository implements WithSimplePersistenceUnit {

  public void registrar(Entidad entidad) {entityManager().persist(entidad);}

  public List<Entidad> todos() {
    return entityManager()
        .createQuery("from Entidad")
        .getResultList();
  }

  public Entidad buscarPorID(Long id) {
    return entityManager().find(Entidad.class, id);
  }


}
