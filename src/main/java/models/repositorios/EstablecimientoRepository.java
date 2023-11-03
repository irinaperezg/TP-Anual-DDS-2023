package models.repositorios;

import models.domain.main.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class EstablecimientoRepository implements WithSimplePersistenceUnit {

  public void registrar(Establecimiento establecimiento) {
    entityManager().persist(establecimiento);
  }

  public List<Establecimiento> todos() {
    return entityManager()
        .createQuery("from Establecimiento")
        .getResultList();
  }

  public Establecimiento buscarPorID(Long id) {
    return entityManager().find(Establecimiento.class, id);
  }

  public List<Establecimiento> obtenerEstablecimientosAsociados(Long comunidadId) {
    try {
      String jpql = "SELECT e FROM Establecimiento e " +
          "JOIN e.comunidades c " +
          "WHERE c.id = :comunidadId";

      return entityManager().createQuery(jpql, Establecimiento.class)
          .setParameter("comunidadId", comunidadId)
          .getResultList();
    } catch (IllegalArgumentException e) {
      // Manejo de IllegalArgumentException
      e.printStackTrace(); // Imprime el error en la consola o registra el error en logs
      // Puedes lanzar la excepción, loggearla, o manejarla específicamente según tu lógica
      // Por ejemplo: throw new MiExcepcionPersonalizada("Error al ejecutar la consulta JPQL", e);
      return null; // O devolver una lista vacía, dependiendo de la lógica de tu aplicación
    } catch (NoResultException e) {
      // Manejo de NoResultException
      e.printStackTrace(); // Manejo del error
      return new ArrayList<>(); // Devolver una lista vacía si no se encuentran resultados
    }  catch (PersistenceException e) {
      // Manejo de PersistenceException
      e.printStackTrace(); // Manejo del error
      // Dependiendo de la causa subyacente, puedes actuar de manera específica
      return null;
    } catch (Exception e) {
      // Manejo de excepciones genéricas
      e.printStackTrace(); // Manejo del error
      // Puedes manejar cualquier otra excepción no capturada anteriormente
      return null;
    }
  }


}
