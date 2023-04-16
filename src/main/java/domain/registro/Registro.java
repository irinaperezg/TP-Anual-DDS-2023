package domain.registro;

import domain.registro.Validaciones.CredencialesPorDefecto;
import domain.registro.Validaciones.CumpleRestriccionesNist;
import domain.registro.Validaciones.NoEsComun;


import java.util.List;

public class Registro {
    private List<Validacion> validaciones;

    private void agregarValidaciones() throws Exception {
        validaciones.add(new CredencialesPorDefecto());
        validaciones.add(new CumpleRestriccionesNist());
        validaciones.add(new NoEsComun());
    }

    private boolean esSegura (String nombre, String contrasenia) {
        //TODO agregar excepciones
        return validaciones.stream().allMatch(validacion -> validacion.validarContrasenia(nombre, contrasenia));
    }

    private void registrarUsuario (String nombre, String contrasenia ) {
        if (!esSegura(nombre, contrasenia)) {

        }
        else {
            Usuario usuario = new Usuario(nombre, contrasenia);
        }
    }
}
