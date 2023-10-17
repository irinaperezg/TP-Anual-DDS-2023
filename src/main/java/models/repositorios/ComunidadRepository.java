package models.repositorios;

import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Miembro;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class ComunidadRepository implements WithSimplePersistenceUnit {

  public void registrar(Comunidad comunidad)
  {
    entityManager().persist(comunidad);
  }

  public void actualizar(Comunidad comunidad){
    entityManager().merge(comunidad);
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
    String queryComunidades = "SELECT DISTINCT c FROM Comunidad c " +
                              "JOIN c.miembros m " +
                              "JOIN m.persona p " +
                              "JOIN p.usuario u " +
                              "WHERE u.id = :usuarioId"; // Usar un parámetro en lugar de incrustar el ID

    return entityManager()
            .createQuery(queryComunidades, Comunidad.class)
            .setParameter("usuarioId", usuario.getId())
            .getResultList();
  }



}
