package models.repositorios;

import models.domain.main.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Comunidad;

import java.util.List;

public class ServicioRepository implements WithSimplePersistenceUnit {

  public void registrar(Servicio servicio)
  {
    entityManager().persist(servicio);
  }

  public List<Servicio> todos() {
    return entityManager()
        .createQuery("from Servicio")
        .getResultList();
  }

  public Servicio buscarPorID(Long id) {
    return entityManager().find(Servicio.class, id);
  }

  public List<Servicio> serviciosDeUnaComunidad (Comunidad comunidad) {
    String queryServicio = "SELECT * FROM Servicio S " +
        "JOIN SERVICIOS_COMUNIDAD SC ON SC.SERVCIO_ID = S.ID" +
        "JOIN COMUNIDAD C ON C.ID = SC.COMUNIDAD_ID " +
        "WHERE C.ID = " +
        comunidad.getId();
    return entityManager().createQuery(queryServicio).getResultList();
  }
}
