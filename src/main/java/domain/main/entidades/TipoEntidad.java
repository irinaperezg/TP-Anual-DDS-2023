package domain.main.entidades;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class TipoEntidad {


  private String tipoEntidad;
  private String tipoEstablecimiento;
}
