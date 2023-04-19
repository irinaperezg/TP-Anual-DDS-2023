package domain.registro.Validaciones;

import domain.excepciones.excepcionesContrasenias.ExcepcionCredencial;
import domain.registro.Validacion;

public class CredencialesPorDefecto implements Validacion {

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) {
        if (nombre.equals(contrasenia)) {
            throw new ExcepcionCredencial("El nombre y la contrasenia deben ser distintos");
        }
        return true;
    }
}
