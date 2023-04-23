package domain.estaciones;

import java.util.Arrays;
import java.util.List;

public class AgrupacionDeServicios {
    private final List<Servicio> servicios;

    public AgrupacionDeServicios(Servicio ... servicios) {
        this.servicios = Arrays.stream(servicios).toList();
    }
}
