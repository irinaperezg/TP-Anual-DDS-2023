package domain.entidades;

import domain.establecimientos.Localizacion;
import domain.establecimientos.Servicio;
import domain.usuarios.Delegado;

import java.util.List;

public class EntidadPrestadora {
    private String denominacion;
    private List<Entidad> entidadesHijas;
    private Delegado delegado;

    public boolean tuvoIncidente (List<Servicio> servicios){
        return entidadesHijas.stream().anyMatch(entidad -> entidad.tuvoIncidente(servicios));
    }

    public boolean estaCerca(Localizacion localizacionConsultada) {
        return entidadesHijas.stream().anyMatch(entidad -> entidad.estaCerca(localizacionConsultada));
    }
}
