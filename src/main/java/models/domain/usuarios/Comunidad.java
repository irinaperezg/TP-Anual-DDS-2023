package models.domain.usuarios;

import models.domain.main.Establecimiento;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.entidades.Entidad;
import models.domain.main.incidentes.Incidente;
import models.domain.main.servicio.Servicio;
import models.domain.main.servicio.ServicioCompuesto;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="comunidad")
public class Comunidad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="descripcion")
  private String descripcion;

  // TODO AGREGAR AL LUCID EL ESTADO
  @Column(name="estaActiva")
  private Boolean estaActiva;

  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Miembro> miembros = new ArrayList<>();
  public List<Miembro> getAdministradores() {
    return miembros.stream()
            .filter(Miembro::getEsAdministrador)
            .toList();
  }

  public List<Miembro> getMiembrosRegulares() {
    return miembros.stream()
            .filter(miembro -> !miembro.getEsAdministrador())
            .toList();
  }

  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Incidente> incidentes = new ArrayList<>();

  // TODO AGREGAR AL LUCID
  @ManyToMany(mappedBy = "comunidades")
  private List<Servicio> serviciosObservados = new ArrayList<>();

  @ManyToMany(mappedBy = "comunidades")
  private List<Establecimiento> establecimientosObservados = new ArrayList<>();

  public void agregarIncidente(Incidente incidente) {
    incidentes.add(incidente);
  }
  public ServicioCompuesto crearServicioCompuesto(String descripcion, Servicio... otrosServicios){
    return new ServicioCompuesto(descripcion, otrosServicios);
  }

  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }

  public Integer obtenerCantidadMiembrosAfectados() {
    return miembros.stream().filter(miembro -> miembro.getTipo().equals(TipoMiembro.AFECTADO)).toList().size();
  }

  public boolean leInteresa (PrestacionDeServicio prestacion) {
    return serviciosObservados.contains(prestacion.getServicio()) && establecimientosObservados.contains(prestacion.getEstablecimiento());
  }
}
