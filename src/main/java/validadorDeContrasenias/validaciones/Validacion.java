package validadorDeContrasenias.validaciones;

import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;

public interface Validacion {
  boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida;
}
