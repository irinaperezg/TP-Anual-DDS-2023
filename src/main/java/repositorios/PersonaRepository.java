package repositorios;

import domain.usuarios.Persona;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class PersonaRepository implements WithSimplePersistenceUnit {

    public void registrar(Persona persona)
    {
      entityManager().persist(persona);
    }

    public List<Persona> todos() {
      return entityManager()
          .createQuery("from Persona")
          .getResultList();
    }

    public Persona buscarPorID(Long id) {
      return entityManager().find(Persona.class, id);
    }
}
