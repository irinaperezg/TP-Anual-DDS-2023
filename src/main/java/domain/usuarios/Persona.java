package domain.usuarios;

import domain.converter.FrecuenciaDeNotificacionAttributeConverter;
import domain.converter.LocalDateTimeAttributeConverter;
import domain.localizacion.main.Localidad;
import domain.main.entidades.Entidad;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import domain.main.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;
import org.quartz.SchedulerException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter

@Entity
@Table(name="persona")
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Miembro> miembros;

  @ManyToOne
  @JoinColumn(name = "localidad_id", referencedColumnName = "id")
  private Localidad localidad = null;

  @Column(name="email", columnDefinition = "TEXT")
  private String email;

  @Column(name="telefono", columnDefinition = "TEXT")
  private String telefono;

  @Column(name="frecuencia_notificacion")
  @Convert(converter = FrecuenciaDeNotificacionAttributeConverter.class)
  private FrecuenciaNotificacion frecuenciaNotification;

  @Enumerated(EnumType.STRING)
  @Column(name="medio_de_notificacion")
  private PreferenciaMedioNotificacion preferenciaMedioNotificacion;

  @ElementCollection
  @CollectionTable(name="horarioDeNotificacion", joinColumns = @JoinColumn(name="persona_id"))
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name="horario")
  private List<LocalDateTime> horariosDeNotificaciones;

  @ManyToMany(mappedBy = "asociados")
  private List<Entidad> entidades = new ArrayList<>();

  @ManyToMany(mappedBy = "asociados")
  private List<Servicio> servicios = new ArrayList<>();


  public Persona(Usuario usuario, String email, String telefono, FrecuenciaNotificacion frecuenciaNotification, PreferenciaMedioNotificacion preferenciaMedioNotificacion, List<LocalDateTime> horariosDeNotificaciones) throws SchedulerException {
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
