package repositorios;

import domain.usuarios.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class ComunidadRepository implements WithSimplePersistenceUnit {
  public void registrar(Comunidad comunidad)
  {
    entityManager().persist(comunidad);
  }

  public List<Comunidad> todas() {
    return entityManager()
        .createQuery("from Comunidad")
        .getResultList();
  }

  public Comunidad comunidadPorID(Long id) {
    return entityManager().find(Comunidad.class, id);
  }
}
