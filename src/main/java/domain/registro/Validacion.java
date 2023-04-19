package domain.registro;

import domain.excepciones.excepcionesContrasenias.ExcepcionLongitud;

public interface Validacion {
    public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionLongitud;
}
