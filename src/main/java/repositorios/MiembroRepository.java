package repositorios;

import domain.usuarios.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

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
}
