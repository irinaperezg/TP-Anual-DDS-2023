package domain.registro.Validaciones;

import domain.registro.Validacion;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CumpleRestriccionesNist implements Validacion {
    @Setter
    private int cantidadDeCaracteresMaxima = 64;
    @Setter
    private int cantidadDeCaracteresMinima = 8;

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) {
        return cumpleLongitud(contrasenia) && cumpleRotacion() && cumpleComplejidad(contrasenia);
    }

    private boolean cumpleLongitud(String contrasenia) {
        return cumpleCaracteresMinima(contrasenia) && cumpleCaracteresMaxima(contrasenia);
    }

    private boolean cumpleCaracteresMinima(String contrasenia) {
        if (contrasenia.length() < cantidadDeCaracteresMinima) {
            //Todo exception
        }
        return true;
    }

    private boolean cumpleCaracteresMaxima(String contrasenia) {
        if (contrasenia.length() > cantidadDeCaracteresMaxima) {
            //Todo exception
        }
        return true;
    }

    private boolean cumpleRotacion() {
        return true; //TODO
    }

    private boolean cumpleComplejidad(String contrasenia) {
        //debe contener un numero, una mayuscula, una minuscula y un caracter especial
        Pattern regex = Pattern.compile("^(?=.{8,})(?=.[a-z])(?=.[0-9])(?=.[A-Z])(?=.[@#$%_^&+=]).*$");
        Matcher matcher = regex.matcher(contrasenia);
        if (!matcher.find()){
            //TODO exception
        }
        return true;
    }
}