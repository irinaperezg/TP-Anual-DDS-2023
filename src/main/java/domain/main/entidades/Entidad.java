package domain.main.entidades;

import domain.localizacion.main.Localidad;
import domain.localizacion.main.Localizacion;
import domain.main.EntidadPrestadora;
import domain.main.Servicio;
import domain.usuarios.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Entidad {
  @Getter
  private TipoEntidad tipo;
  @Getter
  private String denominacion;
  private EntidadPrestadora entidadPrestadora;
  private List<Miembro> miembros = new ArrayList<>();

  public Entidad(TipoEntidad tipo, String denominacion) {
    this.tipo = tipo;
    this.denominacion = denominacion;
  }

  public List<Miembro> buscarInteresados(Localidad localidad, Servicio servicio) {
    List<Miembro> interesados = new ArrayList<>();
    for(Miembro miembro : miembros) {
      if (miembro.getLocalidad().esIgualA(localidad) && servicio.esDeInteresPara(miembro)) {
        interesados.add(miembro);
      }
    }
    return interesados;
  }

  public void agregarMiembros(Miembro ... nuevosMiembros) {
    miembros.addAll(List.of(nuevosMiembros));
  }
}