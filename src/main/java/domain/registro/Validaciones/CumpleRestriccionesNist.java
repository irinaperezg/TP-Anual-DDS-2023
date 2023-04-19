package domain.registro.Validaciones;

import domain.registro.Validacion;
import domain.excepciones.excepcionesContrasenias.ExcepcionComplejidad;
import domain.excepciones.excepcionesContrasenias.ExcepcionLongitud;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CumpleRestriccionesNist implements Validacion {
    @Setter @Getter
    public int cantidadDeCaracteresMaxima = 64;
    @Setter @Getter
    public int cantidadDeCaracteresMinima = 8;

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionLongitud {
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

    public boolean cumpleComplejidad(String contrasenia) {
        Pattern regex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])\\S+$");
        Matcher matcher = regex.matcher(contrasenia);
        if (!matcher.find()){
            throw new ExcepcionComplejidad("La contrasenia debe contener un numero, una mayuscula, una minuscula y un simbolo");
        }
        return true;
    }
}