package models.repositorios;

import models.domain.usuarios.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioRepository implements WithSimplePersistenceUnit {

  public void registrar(Usuario usuario) {
    EntityManager em = entityManager();
    em.getTransaction().begin();
    em.persist(usuario);
    em.getTransaction().commit();
    em.close();
  }

  public List<Usuario> todos() {
    EntityManager em = entityManager();
    List<Usuario> usuarios = em.createQuery("from Usuario").getResultList();
    em.close();
    return usuarios;
  }

  public Usuario buscarPorID(Long id) {
    EntityManager em = entityManager();
    Usuario usuario = em.find(Usuario.class, id);
    em.close();
    return usuario;
  }

  public Usuario buscarPorNombreUsuario(String name) {
    EntityManager em = entityManager();
    Usuario usuario = em.find(Usuario.class, name);
    em.close();
    return usuario;
  }
}
