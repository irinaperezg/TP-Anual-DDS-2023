package domain.main;

import domain.main.entidades.Entidad;
import domain.usuarios.Delegado;
import domain.main.notificaciones.Notificador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name="entidadPrestadora")
public class EntidadPrestadora {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="denominacion", columnDefinition = "TEXT")
    private String denominacion;

    @Setter
    @OneToMany
    private List<Entidad> entidades;

    @OneToOne
    @JoinColumn(name = "organismo_de_control_id", referencedColumnName = "id")
    private OrganismoDeControl organismoDeControl;

    @ManyToOne
    @JoinColumn(name = "delegado_id", referencedColumnName = "id")
    private Delegado delegado;

    public EntidadPrestadora(String denominacion, Delegado delegado) {
        this.denominacion = denominacion;
        this.delegado = delegado;
    }

    public EntidadPrestadora() {

    }
}
