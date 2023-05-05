package validadorDeContrasenias.validaciones;

import validadorDeContrasenias.excepciones.ExcepcionCredencial;
import validadorDeContrasenias.Validacion;

public class CredencialesPorDefecto implements Validacion {
    public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionCredencial {
        if (nombre.equals(contrasenia)) {
            throw new ExcepcionCredencial("El nombre y la contrasenia deben ser distintos");
        }
        return true;
    }
}
