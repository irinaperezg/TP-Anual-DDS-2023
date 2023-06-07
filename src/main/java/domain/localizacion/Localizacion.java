package domain.localizacion;

import lombok.Getter;
import lombok.Setter;


public class Localizacion {
  @Getter
  @Setter
  Departamento departamento;
  @Getter
  @Setter
  Municipio municipio;
  Provincia provincia;

  public boolean estaCerca(Localizacion localizacion) {
    boolean validacionMuni = this.getMunicipio() != null && this.municipio.equals(localizacion.getMunicipio());
    boolean validacionDepto = this.getDepartamento() != null && this.departamento.equals(localizacion.getDepartamento());
    return validacionMuni || validacionDepto;
  }
}
