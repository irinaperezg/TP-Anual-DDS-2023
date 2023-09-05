package validadorDeContrasenias.validaciones;

import java.util.ArrayList;
import java.util.List;
import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleComplejidad;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleLongitud;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleRotacion;
import validadorDeContrasenias.validaciones.restriccionesNist.RestriccionNist;

public class CumpleRestriccionesNist implements Validacion {
  private final List<RestriccionNist> restricciones = new ArrayList<>();

  public CumpleRestriccionesNist() {
    this.agregarRestriccionesNist();
  }

  public void agregarRestriccionesNist() {
    restricciones.add(new CumpleRotacion());
    restricciones.add(new CumpleComplejidad());
    restricciones.add(new CumpleLongitud());
  }

  public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
    return restricciones.stream().allMatch(restriccion ->
        restriccion.cumpleRestriccion(contrasenia));
  }
}