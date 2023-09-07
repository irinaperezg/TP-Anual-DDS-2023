package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.usuarios.Persona;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class FrecuenciaNotificacionBase implements FrecuenciaNotificacion {
    @Id
    @GeneratedValue
    private Long id;
}
