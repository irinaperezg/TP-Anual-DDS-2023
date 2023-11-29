package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.domain.main.informes.rankings.Reporte;

import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import java.util.List;

public class ReportesRepository implements WithSimplePersistenceUnit {



    public List buscarTodos() {
        return null;
    }


    public Object buscar(Long id) {
        return null;
    }


    public void guardar(Reporte o) {
        entityManager().setFlushMode(FlushModeType.COMMIT);
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(o);
        tx.commit();
    }


    public void actualizar(Object o) {

    }


    public void eliminar(Object o) {

    }

    public List<Reporte> buscarPorIdEntidad(Long id) {
        return entityManager().createQuery("from " + Reporte.class.getName() + " where entidad_id = :id ", Reporte.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void guardarTodo(List<Reporte> reportes) {
        for (Reporte reporte : reportes) {
            this.guardar(reporte);
        }
    }
}
