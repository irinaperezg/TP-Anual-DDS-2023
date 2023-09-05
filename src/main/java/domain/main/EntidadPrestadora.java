package domain.main;

import domain.main.entidades.Entidad;
import domain.usuarios.Delegado;
import domain.main.notificaciones.Notificador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class EntidadPrestadora {
    private String denominacion;
    @Setter
    private List<Entidad> entidades;
    private OrganismoDeControl organismoDeControl;
    private Delegado delegado;


    public EntidadPrestadora(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }


}
