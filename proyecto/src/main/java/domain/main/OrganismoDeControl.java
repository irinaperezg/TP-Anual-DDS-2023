package domain.main;

import domain.main.entidades.Entidad;
import domain.main.servicio.Servicio;
import domain.usuarios.Delegado;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "organismoDeControl")
public class OrganismoDeControl {
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    private String denominacion;
    private Delegado delegado;
    private List<Servicio> serviciosControlados;
    private List<Entidad> entidades;

    public OrganismoDeControl(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    //public void notificarProblematicasDelegado() = new Notificador().notificarProblematicas(delegado);
}
