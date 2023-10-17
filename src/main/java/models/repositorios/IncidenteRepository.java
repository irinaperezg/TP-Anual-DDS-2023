package models.repositorios;

import models.domain.main.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Comunidad;
import org.apache.http.conn.util.PublicSuffixList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.hsqldb.Tokens.CURRENT_TIMESTAMP;

public class IncidenteRepository implements WithSimplePersistenceUnit {

  public void registrar(Incidente incidente)
  {
    EntityManager em = entityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin(); // Iniciar la transacción
      em.persist(incidente); // Realizar la operación de persistencia
      tx.commit(); // Confirmar la transacción si todo va bien
    } catch (Exception e) {
      if (tx != null && tx.isActive()) {
        tx.rollback(); // Revertir la transacción en caso de error
      }
      e.printStackTrace(); // Manejar la excepción apropiadamente
    } finally {
      em.close(); // Cerrar el EntityManager cuando hayas terminado
    }
  }

  public List<Incidente> todos() {
    return entityManager()
        .createQuery("SELECT i FROM Incidente i", Incidente.class)
        .getResultList();
  }
  public Incidente buscarPorID(Long id) {
    return entityManager().find(Incidente.class, id);
  }

  //TODO: buscarIncidentesDeTodasLasComunidades
  //public List<Incidente> buscarIncidentesDeTodasLasComunidades (Long comunidadId)

  public void cerrarIncidente(Long id) {
    EntityManager em = entityManager(); // Ensure that you have a properly configured EntityManager
    EntityTransaction transaction = em.getTransaction();

    try {
      transaction.begin();

      Incidente incidente = em.find(Incidente.class, id); // Load the incidente by its ID
      if (incidente != null) {
        incidente.setAbierto(false);
        incidente.setFechaCierre(LocalDateTime.now()); // Set the current timestamp

        em.merge(incidente); // Update the incidente entity in the database
      }

      transaction.commit(); // Commit the transaction
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback(); // Rollback the transaction in case of an exception
      }
      e.printStackTrace(); // Log or handle the exception
    } finally {
      em.close(); // Close the EntityManager when done
    }
  }

  public void borrarTodos() {
    entityManager()
        .createQuery("DELETE FROM Incidente")
        .executeUpdate();
  }
}
