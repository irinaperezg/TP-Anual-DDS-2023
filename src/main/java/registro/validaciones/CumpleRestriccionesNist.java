package registro.validaciones;

import excepciones.contrasenias.ExcepcionContraseniaInvalida;
import registro.Validacion;
import excepciones.contrasenias.ExcepcionComplejidad;
import excepciones.contrasenias.ExcepcionLongitud;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CumpleRestriccionesNist implements Validacion {
    public int cantidadDeCaracteresMaxima = 64;
    public int cantidadDeCaracteresMinima = 8;

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
        return cumpleLongitud(contrasenia) && cumpleRotacion() && cumpleComplejidad(contrasenia);
    }

    public boolean cumpleLongitud(String contrasenia) throws ExcepcionLongitud {
        return cumpleCaracteresMinima(contrasenia) && cumpleCaracteresMaxima(contrasenia);
    }

    public boolean cumpleCaracteresMinima(String contrasenia) throws ExcepcionLongitud {
        if (contrasenia.length() < cantidadDeCaracteresMinima) {
            throw new ExcepcionLongitud("La contrasenia debe tener al menos " + cantidadDeCaracteresMinima + " caracteres");
        }
        return true;
    }

    public boolean cumpleCaracteresMaxima(String contrasenia) throws ExcepcionLongitud {
        if (contrasenia.length() > cantidadDeCaracteresMaxima) {
            throw new ExcepcionLongitud("La contrasenia debe tener menos de " + cantidadDeCaracteresMaxima + " caracteres");
        }
        return true;
    }

    public boolean cumpleRotacion() {
        return true; //TODO
    }

    public boolean cumpleComplejidad(String contrasenia) throws ExcepcionComplejidad {
        Pattern regex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])\\S+$");
        Matcher matcher = regex.matcher(contrasenia);
        if (!matcher.find()){
            throw new ExcepcionComplejidad("La contrasenia debe contener un numero, una mayuscula, una minuscula y un simbolo");
        }
        return true;
    }
}