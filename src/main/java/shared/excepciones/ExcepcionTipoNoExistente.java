package shared.excepciones;

public class ExcepcionTipoNoExistente extends RuntimeException {

  public ExcepcionTipoNoExistente(String message) {
    super(message);
  }
}
