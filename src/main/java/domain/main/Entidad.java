package domain.main;

import domain.localizacion.Localizacion;
import domain.usuarios.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Entidad {
  @Getter
  private String denominacion;
  private EntidadPrestadora entidadPrestadora;
  private List<Miembro> miembros = new ArrayList<>();

  public Entidad(String denominacion) {
    this.denominacion = denominacion;
  }

  public List<Miembro> buscarInteresados(Localizacion localizacion, Servicio servicio) {
    List<Miembro> interesados = new ArrayList<>();
    for(Miembro miembro : miembros) {
      if (miembro.getLocalizacion().estaCerca(localizacion) && servicio.esDeInteresPara(miembro)) {
        interesados.add(miembro);
      }
    }
    return interesados;
  }

  public void agregarMiembros(Miembro ... nuevosMiembros) {
    miembros.addAll(List.of(nuevosMiembros));
  }
}