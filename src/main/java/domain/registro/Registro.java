package domain.registro;

import domain.usuarios.Persona;

import java.util.List;

public class Registro {
    private List <Validacion> validaciones;

    private boolean esSegura (String contrasenia) {
        return validaciones.stream().allMatch(validacion -> validacion.validarContrasenia(contrasenia));
    }
    private void registrarUsuario (String nombre, String contrasenia ) {
        if (!esSegura(contrasenia)) {

        }
        else {
            Persona persona = new Persona (nombre, contrasenia);
        }
    }
}
