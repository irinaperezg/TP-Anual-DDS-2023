package domain.entidades;

import domain.usuarios.Delegado;
import lombok.Getter;

import java.util.List;

public class OrganismoDeControl {
    @Getter
    private String denominacion;
    private Delegado delegado;
    private List<EntidadPrestadora> entidadPrestadorasControladas;

    public OrganismoDeControl(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public void notificarProblematicasDelegado() {
        //TODO
    }
}
