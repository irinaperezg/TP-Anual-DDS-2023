package validadorDeContrasenias.excepciones;

public class ExcepcionContraseniaInvalida extends RuntimeException {

  public ExcepcionContraseniaInvalida(String message) {
    super(message);
  }
}
