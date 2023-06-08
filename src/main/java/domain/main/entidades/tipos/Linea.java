package domain.main.entidades.tipos;

import domain.main.entidades.TipoEntidad;
import lombok.Getter;

@Getter
public class Linea extends TipoEntidad {
  private final String tipoEntidad = "Linea";
  private final String tipoEstablecimiento = "Estacion";
}
