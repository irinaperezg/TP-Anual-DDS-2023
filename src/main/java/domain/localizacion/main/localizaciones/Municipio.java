package domain.localizacion.main.localizaciones;

import domain.localizacion.main.Localizacion;
import domain.localizacion.main.Provincia;
import lombok.Getter;

@Getter
public class Municipio implements Localizacion {
  private final Integer id;
  private final String nombre;
  private final Provincia provincia;
  private final TipoLocalizacion tipoLocalizacion = TipoLocalizacion.Municipio;

  public Municipio(Integer id, String nombre, Provincia provincia) {
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
  }

  public boolean esIgualA(Localizacion localizacion) {
    return this.id.equals(localizacion.getId()) && this.nombre.equals(localizacion.getNombre()) && this.provincia.esIgualA(localizacion.getProvincia());
  }
}
