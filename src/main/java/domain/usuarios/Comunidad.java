package domain.usuarios;

import domain.Persistente;
import domain.main.incidentes.Incidente;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioCompuesto;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="comunidad")
public class Comunidad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="descripcion")
  private String descripcion;

  @Getter
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
  /*@Getter
  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Miembro> miembros = new ArrayList<>();

  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Miembro> administradores= new ArrayList<>();*/

  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Incidente> incidentes = new ArrayList<>();

  public void agregarIncidente(Incidente incidente) {
    incidentes.add(incidente);
  }
  public ServicioCompuesto crearServicioCompuesto(String descripcion, Servicio... otrosServicios){
    return new ServicioCompuesto(descripcion, otrosServicios);
  }

  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }
}
