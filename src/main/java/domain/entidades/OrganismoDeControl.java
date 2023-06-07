package domain.entidades;

import lombok.Getter;

import java.util.List;

public class OrganismoDeControl {
    @Getter
    private String denominacion;
    private Delegado delegado;
    private List<EntidadPrestadora> entidadPrestadorasControladas;
    private List<Servicio> serviciosControlados;

    public OrganismoDeControl(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public void notificarProblematicasDelegado() {
        //TODO
    }
}
