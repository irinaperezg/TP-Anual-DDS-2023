package domain.usuarios;

import domain.estaciones.AgrupacionDeServicios;
import domain.estaciones.Servicio;

import java.util.List;

public class Comunidad {
    private List<Miembro> miembros;
    private List<Miembro> administradores;

    public AgrupacionDeServicios agregarAgrupacionDeServicios(Servicio ... servicios) {
        return new AgrupacionDeServicios(servicios);
    }
}
