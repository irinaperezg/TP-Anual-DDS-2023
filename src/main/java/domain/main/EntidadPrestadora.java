package domain.main;

import domain.usuarios.Delegado;
import domain.usuarios.Notificador;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class EntidadPrestadora {
    @Getter
    private String denominacion;
    private OrganismoDeControl organismoDeControl;
    private Delegado delegado;

    public EntidadPrestadora(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public void notificarProblematicasDelegado() {
        new Notificador().notificarProblematicas(delegado);
    }
}
