package repositorios;

    import domain.main.incidentes.Incidente;
    import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

    import java.util.List;

public class IncidenteRepository implements WithSimplePersistenceUnit {
  public void registrar(Incidente incidente)
  {
    entityManager().persist(incidente);
  }

  public List<Incidente> todos() {
    return entityManager()
        .createQuery("from Incidente")
        .getResultList();
  }

  public Incidente incidentePorId(Long id) {
    return entityManager().find(Incidente.class, id);
  }
}
