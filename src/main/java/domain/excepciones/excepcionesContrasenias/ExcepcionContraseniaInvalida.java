package domain.excepciones.excepcionesContrasenias;

public class ExcepcionContraseniaInvalida extends RuntimeException {

  public ExcepcionContraseniaInvalida(String message) {
    super(message);
  }
}
