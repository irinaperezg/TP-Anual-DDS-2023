package models.repositorios;

import models.domain.apis.georef.ServicioGeoref;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
import models.domain.main.localizacion.Provincia;
import models.domain.main.localizacion.TipoLocalizacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.usuarios.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class LocalizacionRepository implements WithSimplePersistenceUnit {

  private ServicioGeoref servicioGeoref;
  public void registrar(Localizacion localizacion)
  {
    entityManager().persist(localizacion);
  }

  public List<Localizacion> todos() {
    return entityManager()
        .createQuery("from Localizacion")
        .getResultList();
  }

  public void persistirLocalidades() {
  // TODO
  }

  public void persistirProvincias(List<Provincia> provincias) {
    //TODO
  }

  public List<Localidad> todasLasLocalidades() {
    return entityManager()
            .createQuery("from Localidad", Localidad.class)
            .getResultList();
  }

  public List<Provincia> todasLasProvincias() {
    return entityManager()
        .createQuery("from Provincia ", Provincia.class)
        .getResultList();
  }

  public Localizacion buscarPorID(Long id) {
    return entityManager().find(Localizacion.class, id);
  }

  public List<Localizacion> buscarPorTipo(TipoLocalizacion tipoLocalizacion) {
    String jpql = "SELECT l FROM Localizacion l WHERE l.tipoLocalizacion = :tipo";
    return entityManager()
        .createQuery(jpql, Localizacion.class)
        .setParameter("tipo", tipoLocalizacion)
        .getResultList();
  }

  public Localidad buscarLocalidadPorId(Long localidadId) {
    return entityManager().find(Localidad.class, localidadId);
  }

  public void registrarLocalidad(Localidad localidad) {
    EntityManager em = entityManager();
    EntityTransaction tx = null;

    try {
      tx = em.getTransaction();
      tx.begin();
      em.persist(localidad);
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
}

