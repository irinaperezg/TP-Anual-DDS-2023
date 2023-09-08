package domain.localizacion.main.localizaciones;

import domain.localizacion.main.Localizacion;
import domain.localizacion.main.Provincia;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class Municipio implements Localizacion {
  @Transient
  private Integer id;

  private String nombre;
  @Embedded
  private Provincia provincia;

  @Enumerated(EnumType.STRING)
  private final TipoLocalizacion tipoLocalizacion = TipoLocalizacion.Municipio;

  public Municipio(Integer id, String nombre, Provincia provincia) {
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
  }

  public Municipio() {

  }

  public boolean esIgualA(Localizacion localizacion) {
    return this.id.equals(localizacion.getId()) && this.nombre.equals(localizacion.getNombre()) && this.provincia.esIgualA(localizacion.getProvincia());
  }
}
