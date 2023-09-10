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
@Table(name="entidad_prestadora")
public class EntidadPrestadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="denominacion", columnDefinition = "TEXT")
    private String denominacion;

    @Setter
    @OneToMany(mappedBy = "entidadPrestadora", cascade = CascadeType.ALL, orphanRemoval = true)
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
