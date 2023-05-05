package validadorDeContrasenias;

import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import validadorDeContrasenias.validaciones.CredencialesPorDefecto;
import validadorDeContrasenias.validaciones.restriccionesNist.CumpleRestriccionesNist;
import validadorDeContrasenias.validaciones.NoEsComun;

import static config.Config.hash;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContrasenia {
    private final List<Validacion> validaciones = new ArrayList<>();
    public ValidadorDeContrasenia() {
        this.agregarValidaciones();
    }

    public void agregarValidaciones() {
        validaciones.add(new CredencialesPorDefecto());
        validaciones.add(new CumpleRestriccionesNist());
        validaciones.add(new NoEsComun());
    }

    public boolean verificarValidez(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
        return validaciones.stream().allMatch(validacion ->
            validacion.validarContrasenia(nombre, contrasenia));
    }

    public String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
