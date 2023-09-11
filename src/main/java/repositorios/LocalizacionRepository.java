package repositorios;

import domain.localizacion.main.Localizacion;
import domain.localizacion.main.TipoLocalizacion;
import domain.main.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class LocalizacionRepository implements WithSimplePersistenceUnit {
  public void registrar(Localizacion localizacion)
  {
    entityManager().persist(localizacion);
  }

  public List<Localizacion> todos() {
    return entityManager()
        .createQuery("from Localizacion")
        .getResultList();
  }

  public Localizacion localizacionPorId(Long id) {
    return entityManager().find(Localizacion.class, id);
  }

  public List<Localizacion> localizacionPorTipo(TipoLocalizacion tipoLocalizacion) {
    String jpql = "SELECT l FROM Localizacion l WHERE l.tipoLocalizacion = :tipo";
    return entityManager()
        .createQuery(jpql, Localizacion.class)
        .setParameter("tipo", tipoLocalizacion)
        .getResultList();
  }
}