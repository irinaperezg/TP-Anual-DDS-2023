package registro.validaciones;

import excepciones.contrasenias.ExcepcionCredencial;
import registro.Validacion;

public class CredencialesPorDefecto implements Validacion {
    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionCredencial {
        if (nombre.equals(contrasenia)) {
            throw new ExcepcionCredencial("El nombre y la contrasenia deben ser distintos");
        }
        return true;
    }
}
