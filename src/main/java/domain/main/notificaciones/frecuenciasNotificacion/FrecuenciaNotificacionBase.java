package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.usuarios.Persona;

import javax.persistence.*;

//TODO: CAMBIAR PARA QUE ESTE EN LA MISTA TABLA QUE PERSONA Y NO EN OTRA APARTE
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo")
public abstract class FrecuenciaNotificacionBase implements FrecuenciaNotificacion {
    @Id
    @GeneratedValue
    private Long id;
}
