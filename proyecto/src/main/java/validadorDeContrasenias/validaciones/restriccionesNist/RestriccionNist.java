package validadorDeContrasenias.validaciones.restriccionesNist;

import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;

public interface RestriccionNist {
  boolean cumpleRestriccion(String contrasenia) throws ExcepcionContraseniaInvalida;
}
