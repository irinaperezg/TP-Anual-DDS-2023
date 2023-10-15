package models.repositorios;

import models.domain.usuarios.Persona;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
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
}

