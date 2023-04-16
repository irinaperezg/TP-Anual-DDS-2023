package domain.registro.Validaciones;

import domain.registro.Validacion;
import lombok.Setter;

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
        return tieneNumero(contrasenia) && tieneMayuscula(contrasenia) && tieneMinima(contrasenia) && tieneCaracterEspecial(contrasenia);
    }

    private boolean tieneNumero(String contrasenia) {
        return true; //TODO
    }

    private boolean tieneMinima(String contrasenia) {
        return true; //TODO
    }

    private boolean tieneMayuscula(String contrasenia) {
        return true; //TODO
    }

    private boolean tieneCaracterEspecial(String contrasenia) {
        return true; //TODO
    }

    /* COMPLEJIDAD SACADO DE INTERNET
    public void validar(String contrasena) throws Exception {
        Pattern regex = Pattern.compile("^(?=.{8,})(?=.[a-z])(?=.[0-9])(?=.[A-Z])(?=.[@#$%_^&+=]).*$");
        Matcher matcher = regex.matcher(contrasena);
        if (!matcher.find()){
            throw new ValidadorContrasenaException("La contrasena debe contener al menos un numero, una mayuscula, una minuscula y un caracter especial");
        }
    }
     */

}