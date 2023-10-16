package models.repositorios;

import models.domain.usuarios.Persona;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class PersonaRepository implements WithSimplePersistenceUnit {

    public void registrar(Persona persona) {
        EntityManager em = entityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(persona);
            tx.commit();
        } catch (PersistenceException pe) {
            System.out.println("Error de persistencia: " + pe.getMessage());
            pe.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } catch (IllegalStateException ise) {
            System.out.println("Estado ilegal: " + ise.getMessage());
            ise.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } catch (Exception e) {
            System.out.println("Excepción general: " + e.getMessage());
            e.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }


    public List<Persona> todos() {
        EntityManager em = entityManager();
        List<Persona> personas = em.createQuery("from Persona", Persona.class).getResultList();
        em.close();
        return personas;
    }

    public Persona buscarPorID(Long id) {
        EntityManager em = entityManager();
        Persona persona = em.find(Persona.class, id);
        em.close();
        return persona;
    }

    public Persona buscarPorUsuario(Usuario usuario) {
        EntityManager em = entityManager();
        try {
            Persona persona = em.createQuery(
                            "SELECT p FROM Persona p WHERE p.usuario = :usuario",
                            Persona.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();
            return persona;
        } catch (NoResultException e) {
            return null;  // No se encontró una Persona para el Usuario dado
        } finally {
            em.close();
        }
    }

    public Persona buscarPorIDUsuario(Long usuarioId) {
        EntityManager em = entityManager();
        try {
            Persona persona = em.createQuery(
                            "SELECT p FROM Persona p WHERE p.usuario.id = :usuarioId",
                            Persona.class)
                    .setParameter("usuarioId", usuarioId)
                    .getSingleResult();
            return persona;
        } catch (NoResultException e) {
            return null;  // No se encontró una Persona para el ID de Usuario dado
        } finally {
            em.close();
        }
    }


}



