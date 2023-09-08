package domain.usuarios;

import domain.localizacion.main.Localidad;
import domain.main.notificaciones.frecuenciasNotificacion.Calendario;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacionBase;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import lombok.Getter;
import lombok.Setter;
import org.quartz.SchedulerException;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="persona")
@Getter @Setter
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private Usuario usuario;

  @Transient
  private List<Miembro> miembros;

  @Transient
  //@ManyToOne
  //@JoinColumn(name = "localidad_id", referencedColumnName = "id")
  private Localidad localidad = null;

  @Column(name="email", columnDefinition = "TEXT")
  private String email;

  @Column(name="telefono", columnDefinition = "TEXT")
  private String telefono;

  @Transient
  // @OneToOne
 // @JoinColumn(name = "frecuencia_id", referencedColumnName = "id")
  private FrecuenciaNotificacionBase frecuenciaNotification;

  @Enumerated(EnumType.STRING)
  @Column(name="medio_de_notificacion")
  private PreferenciaMedioNotificacion preferenciaMedioNotificacion;

  @Transient
  //@ElementCollection
  //@CollectionTable(name="horariosDeNotificacion", joinColumns = @JoinColumn(name="persona_id"))
  //@Column(name="horarios")
  private List<LocalDateTime> horariosDeNotificaciones;


  public Persona(Usuario usuario, String email, String telefono, FrecuenciaNotificacionBase frecuenciaNotification, PreferenciaMedioNotificacion preferenciaMedioNotificacion, List<LocalDateTime> horariosDeNotificaciones) throws SchedulerException {
    this.usuario = usuario;
    this.email = email;
    this.telefono = telefono;
    this.frecuenciaNotification = frecuenciaNotification;
    this.preferenciaMedioNotificacion = preferenciaMedioNotificacion;
    this.horariosDeNotificaciones = horariosDeNotificaciones;
  }

  public Persona() {

  }

  public List<Comunidad> getComunidades() {
    return miembros.stream().map(miembro -> miembro.getComunidad()).toList();
  }

  public Long getId() {
    return id;
  }
}
