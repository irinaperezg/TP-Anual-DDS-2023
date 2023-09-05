package domain.localizacion.main;

import domain.localizacion.main.localizaciones.TipoLocalizacion;
import lombok.Getter;

public interface Localizacion {

  boolean esIgualA(Localizacion localizacion);

  Integer getId();

  String getNombre();

  Provincia getProvincia();

  TipoLocalizacion getTipoLocalizacion();
}
