package domain.main;

import domain.usuarios.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
  @Getter
  private String descripcion;
  private List<Miembro> miembros = new ArrayList<>();

  public Servicio(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean esDeInteresPara(Miembro miembro) {
    return miembros.contains(miembro);
  }

  public void agregarMiembros(Miembro ... nuevosMiembros) {
    miembros.addAll(List.of(nuevosMiembros));
  }
}
