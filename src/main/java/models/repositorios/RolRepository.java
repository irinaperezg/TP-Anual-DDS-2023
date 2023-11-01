package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Persona;
import models.domain.usuarios.roles.Rol;
import models.domain.usuarios.roles.TipoRol;

import java.util.List;

public class RolRepository implements WithSimplePersistenceUnit {
  public void registrar(Rol rol)
  {
    entityManager().persist(rol);
  }

  public List<Rol> todos() {
    return entityManager()
        .createQuery("from Rol")
        .getResultList();
  }

  public Rol buscarPorID(Long id) {
    return entityManager().find(Rol.class, id);
  }
  public Rol buscarPorTipoRol(TipoRol tipoRol) {
    Rol rol = entityManager().createQuery(
            "SELECT r FROM Rol r WHERE r.tipo = :tiporol",Rol.class)
        .setParameter("tiporol", tipoRol)
        .getSingleResult();
    return rol;
  }

}
