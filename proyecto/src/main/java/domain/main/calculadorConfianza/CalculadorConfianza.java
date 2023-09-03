package domain.main.calculadorConfianza;

import domain.usuarios.Comunidad;

public class CalculadorConfianza {
  private AdapterCalculadorConfianza adapter;
  public Integer calcularGradoConfianza(Comunidad comunidad)
  {
    return adapter.calcularGradoConfianza(comunidad);
  }
}
