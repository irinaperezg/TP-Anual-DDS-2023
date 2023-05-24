package validadorDeContrasenias.validaciones;

import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import validadorDeContrasenias.Validacion;
import validadorDeContrasenias.excepciones.ExcepcionComplejidad;
import validadorDeContrasenias.excepciones.ExcepcionLongitud;
import validadorDeContrasenias.validaciones.CredencialesPorDefecto;
import validadorDeContrasenias.validaciones.NoEsComun;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleComplejidad;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleLongitud;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleRotacion;
import validadorDeContrasenias.validaciones.restriccionesNist.RestriccionNist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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