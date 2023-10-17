package models.repositorios;

import models.domain.main.servicio.Servicio;
import models.domain.usuarios.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.*;
import java.util.List;

public class ComunidadRepository implements WithSimplePersistenceUnit {

  public void registrar(Comunidad comunidad) {
    entityManager().persist(comunidad);
  }

  public void actualizar(Comunidad comunidad) {
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

  public Miembro agregarPersonaAComunidad(Persona persona, Comunidad comunidad, String tipoMiembroStr) {


    TipoMiembro tipoMiembro = TipoMiembro.valueOf(tipoMiembroStr.toUpperCase()); // Convertir el string a su enum correspondiente.
    Miembro miembro = new Miembro();
    miembro.setPersona(persona);
    miembro.setComunidad(comunidad);
    miembro.setTipo(tipoMiembro);
    miembro.setEsAdministrador(false);
    return miembro;
  }

}

