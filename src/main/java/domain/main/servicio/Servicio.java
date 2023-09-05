package domain.main.servicio;

import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Servicio {
  @Getter
  private String descripcion;
  private List<Persona> asociados = new ArrayList<>();

  public Servicio(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean esDeInteresPara(Persona persona) {
    return asociados.contains(persona);
  }

  public void agregarAsociado(Persona persona) {
    asociados.add(persona);
  }
}
