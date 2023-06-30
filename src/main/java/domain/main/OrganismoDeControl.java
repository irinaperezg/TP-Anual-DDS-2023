package domain.main;

import domain.main.entidades.Entidad;
import domain.usuarios.Delegado;
import domain.usuarios.Notificador;
import lombok.Getter;
import java.util.List;

public class OrganismoDeControl {
    @Getter
    private String denominacion;
    private Delegado delegado;
    private List<Servicio> serviciosControlados;
    private List<Entidad> entidades;

    public OrganismoDeControl(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public void notificarProblematicasDelegado() {
        new Notificador().notificarProblematicas(delegado);
    }
}
