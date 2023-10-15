package models.repositorios;

import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Usuario;

import java.util.List;

public class ComunidadRepository implements WithSimplePersistenceUnit {

  public void registrar(Comunidad comunidad)
  {
    entityManager().persist(comunidad);
  }

  public List<Comunidad> todos() {
    return entityManager()
        .createQuery("from Comunidad")
        .getResultList();
  }

  public Comunidad buscarPorID(Long id) {
    return entityManager().find(Comunidad.class, id);
  }

  public List<Comunidad> buscarComunidadesUsuario(Usuario usuario) {

    String queryComunidades = "SELECT * FROM Comunidad C " +
        "JOIN miembros M on M.comunidad_id = C.id " +
        "JOIN personas P on P.id = M.persona_id " +
        "JOIN usuario U on U.id = P.usuario_id "  +
        usuario.getId();

    return entityManager().createQuery(queryComunidades).getResultList();
  }
}
