package domain.entidades;

import domain.localizacion.Localizacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class EntidadPrestadora {
    @Getter
    private String denominacion;
    private List<Entidad> entidadesHijas = new ArrayList<>();
    private Delegado delegado;

    public EntidadPrestadora(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public boolean tuvoIncidente (List<Servicio> servicios){
        return entidadesHijas.stream().anyMatch(entidad -> entidad.tuvoIncidente(servicios));
    }

    public boolean estaCerca(Localizacion localizacionConsultada) {
        return entidadesHijas.stream().anyMatch(entidad -> entidad.estaCerca(localizacionConsultada));
    }

    public void notificarProblematicasDelegado() {
        //TODO
    }
}
