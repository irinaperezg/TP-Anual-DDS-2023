package domain.main.servicio;

import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo")
public abstract class Servicio {

  @Id
  @GeneratedValue
  private Long id;

  @Getter
  @Column(name="descripcion")
  private String descripcion;

  @OneToMany
  private List<Persona> asociados = new ArrayList<>();

  public Servicio(String descripcion) {
    this.descripcion = descripcion;
  }

  public Servicio() {

  }

  public boolean esDeInteresPara(Persona persona) {
    return asociados.contains(persona);
  }

  public void agregarAsociado(Persona persona) {
    asociados.add(persona);
  }
}
