package models.repositorios;

import models.domain.usuarios.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class UsuarioRepository implements WithSimplePersistenceUnit {

  public void registrar(Usuario usuario) {entityManager().persist(usuario);}

  public List<Usuario> todos() {
    return entityManager()
        .createQuery("from Usuario")
        .getResultList();
  }

  public Usuario buscarPorID(Long id) {
    return entityManager().find(Usuario.class, id);
  }
}
