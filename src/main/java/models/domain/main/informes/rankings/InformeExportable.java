package models.domain.main.informes.rankings;

import models.domain.main.exportar.Exportable;

import java.util.List;
import java.util.Map;

public class InformeExportable implements Exportable {
    private String descripcion;
    private Map<String, List<String>> datos;

    public InformeExportable(String descripcion, Map<String, List<String>> datos) {
        this.descripcion = descripcion;
        this.datos = datos;
    }

    @Override
    public String descripcion() {
        return descripcion;
    }

    @Override
    public void setNombre(String nombre) {

    }

    @Override
    public Map<String, List<String>> getDatos() {
        return datos;
    }

    // Puedes agregar métodos adicionales según sea necesario
}
