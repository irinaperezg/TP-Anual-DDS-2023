package registro;

import excepciones.contrasenias.ExcepcionContraseniaInvalida;
import registro.encriptadores.MD5;
import registro.encriptadores.SHA256;
import registro.validaciones.CredencialesPorDefecto;
import registro.validaciones.CumpleRestriccionesNist;
import registro.validaciones.NoEsComun;
import domain.usuarios.Usuario;
import static config.Config.hash;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
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

    public void registrarUsuario(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida, NoSuchAlgorithmException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        verificarValidez(nombre, contrasenia);
        Usuario usuario = new Usuario(nombre, encriptarContrasenia(contrasenia));
        //TODO persistir usuario en base de datos
    }

    public boolean verificarValidez(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
        return validaciones.stream().allMatch(validacion ->
            validacion.validarContrasenia(nombre, contrasenia));
    }

    private String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Obtener la clase correspondiente al nombre del algoritmo de hash
        Class<? extends Encriptador> clazz = Class.forName(hash).asSubclass(Encriptador.class);
        // Crear una instancia de la clase
        Encriptador encriptador = clazz.getDeclaredConstructor().newInstance();

        //Encriptador encriptador = switch (hash) {
        //    case "SHA-256" -> new SHA256();
        //    case "MD5" -> new MD5();
        //    default -> new SHA256();         //ESTO DEBERÍA TIRAR UNA EXCEPCION
        //};
        return encriptador.encriptarContrasenia(contrasenia);
    }
}
