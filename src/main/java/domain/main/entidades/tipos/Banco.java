package domain.main.entidades.tipos;

import domain.main.entidades.TipoEntidad;
import lombok.Getter;

@Getter
public class Banco extends TipoEntidad {
  private final String tipoEntidad = "Banco";
  private final String tipoEstablecimiento = "Sucursal";
}
