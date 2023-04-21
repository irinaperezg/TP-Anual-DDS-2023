package excepciones.contrasenias;

public class ExcepcionContraseniaInvalida extends RuntimeException {

  public ExcepcionContraseniaInvalida(String message) {
    super(message);
  }
}
