package domain.registro.Validaciones;

import domain.registro.Validacion;

public class CredencialesPorDefecto implements Validacion {

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) {
        if (nombre.equals(contrasenia)) {
            //Todo exception
        }
        return true;
    }
}
