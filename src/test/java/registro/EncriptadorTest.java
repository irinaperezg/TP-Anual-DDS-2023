package registro;

import registro.Encriptador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncriptadorTest {
  private final Encriptador encriptador = new Encriptador();

  @Test
  @DisplayName("Se comprueba que funciona el algoritmo de encriptamiento")
  public void contraseniaEncriptadaMD5() throws NoSuchAlgorithmException {
    String hashCorrecto = "4d186321c1a7f0f354b297e8914ab240";
    String miHash = encriptador.encriptarContrasenia("hola","MD5");

    assertEquals(hashCorrecto, miHash);
  }
}
