package models.repositorios;

import models.domain.usuarios.Persona;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonaRepository implements WithSimplePersistenceUnit {

    public void registrar(Persona persona) {
        EntityManager em = entityManager();
        em.getTransaction().begin();

        // Verificar si el usuario ya está persistido
        Usuario usuarioExistente = em.find(Usuario.class, persona.getUsuario().getId());

        if (usuarioExistente == null) {
            // Si el usuario no existe, persistirlo primero
            em.persist(persona.getUsuario());
        } else {
            // Reasociar con el usuario existente
            persona.setUsuario(usuarioExistente);
        }

        em.persist(persona);
        em.getTransaction().commit();
        em.close();
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

