package registro;

import excepciones.contrasenias.ExcepcionContraseniaInvalida;

public interface Validacion {
    boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida;
}
