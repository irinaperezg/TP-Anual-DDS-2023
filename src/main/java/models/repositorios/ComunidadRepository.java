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


  public void asociarServicios(List<Comunidad> comunidades) {
    for (Comunidad comunidad : comunidades) {
      try {
        Comunidad comunidadConServicios = entityManager().createQuery(
            "SELECT c FROM Comunidad c JOIN FETCH c.serviciosObservados WHERE c.id = :id",
            Comunidad.class
        ).setParameter("id", comunidad.getId()).getSingleResult();

        comunidad.setServiciosObservados(comunidadConServicios.getServiciosObservados());
      } catch (IllegalArgumentException e) {
        System.out.println("Error en la consulta para comunidad con ID " + comunidad.getId() + ": Argumentos incorrectos.");
      } catch (NoResultException e) {
        System.out.println("No se encontró la Comunidad con el ID " + comunidad.getId() + ".");
      } catch (NonUniqueResultException e) {
        System.out.println("Múltiples Comunidades con el ID " + comunidad.getId() + " encontradas.");
      } catch (IllegalStateException e) {
        System.out.println("Consulta invocada para un tipo incorrecto en comunidad con ID " + comunidad.getId() + ".");
      } catch (PersistenceException e) {
        System.out.println("Error de persistencia en la base de datos para comunidad con ID " + comunidad.getId() + ".");
      } catch (Exception e) {
        System.out.println("Error no especificado al asociar servicios para comunidad con ID " + comunidad.getId() + ".");
      }
    }
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

