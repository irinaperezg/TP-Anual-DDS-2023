package domain.registro;

import domain.registro.Validaciones.CredencialesPorDefecto;
import domain.registro.Validaciones.CumpleRestriccionesNist;
import domain.registro.Validaciones.NoEsComun;
import domain.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Registro {
    private final List<Validacion> validaciones = new ArrayList<>();

    public Registro() {
        this.agregarValidaciones();
    }

    private void agregarValidaciones() {
        validaciones.add(new CredencialesPorDefecto());
        validaciones.add(new CumpleRestriccionesNist());
        validaciones.add(new NoEsComun());
    }

    public boolean esValido(String nombre, String contrasenia) {
        return validaciones.stream().allMatch(validacion ->
            validacion.validarContrasenia(nombre, contrasenia));
    }

    private void registrarUsuario (String nombre, String contrasenia ) {
        if (esValido(nombre, contrasenia)) {
            Usuario usuario = new Usuario(nombre, contrasenia);
        }
    }
}
