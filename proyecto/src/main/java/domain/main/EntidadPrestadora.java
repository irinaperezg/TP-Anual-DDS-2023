package domain.main;

import domain.main.entidades.Entidad;
import domain.usuarios.Delegado;
import domain.main.notificaciones.Notificador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Entity
@Table(name = "entidadPrestadora")
public class EntidadPrestadora {
    @Id
    @GeneratedValue
    private Long id;
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
