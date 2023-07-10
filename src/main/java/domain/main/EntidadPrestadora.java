package domain.main;

import domain.main.entidades.Entidad;
import domain.usuarios.Delegado;
import domain.main.notificaciones.Notificador;
import lombok.Getter;

import java.util.List;

public class EntidadPrestadora {
    @Getter
    private String denominacion;
    private OrganismoDeControl organismoDeControl;
    private Delegado delegado;
    private List<Entidad> entidades;

    public EntidadPrestadora(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    //public void notificarProblematicasDelegado() {
    //    Notificador.obtenerInstancia().notificarProblematicas(delegado);
    //}

    public void obtenerInformeSemanal(){
        //TODO
    }
}
