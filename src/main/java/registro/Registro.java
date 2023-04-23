package registro;

import excepciones.contrasenias.ExcepcionContraseniaInvalida;
import registro.validaciones.CredencialesPorDefecto;
import registro.validaciones.CumpleRestriccionesNist;
import registro.validaciones.NoEsComun;
import domain.usuarios.Usuario;
import static config.Config.hash;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Registro {
    private final List<Validacion> validaciones = new ArrayList<>();
    Encriptador encriptador = new Encriptador();

    public Registro() {
        this.agregarValidaciones();
    }

    private void agregarValidaciones() {
        validaciones.add(new CredencialesPorDefecto());
        validaciones.add(new CumpleRestriccionesNist());
        validaciones.add(new NoEsComun());
    }

    public void registrarUsuario(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida, NoSuchAlgorithmException {
        verificarValidez(nombre, contrasenia);
        Usuario usuario = new Usuario(nombre, encriptarContrasenia(contrasenia));
        //TODO persistir usuario en base de datos
    }

    public boolean verificarValidez(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
        return validaciones.stream().allMatch(validacion ->
            validacion.validarContrasenia(nombre, contrasenia));
    }

    private String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException {
        return encriptador.encriptarContrasenia(contrasenia, hash);
    }
}
