package domain.localizacion.main;

import lombok.Getter;

@Getter
public abstract class Localizacion {
  private Integer id;
  private String nombre;
  private Provincia provincia;

  public abstract boolean esIgualA(Localizacion localizacion);
}
