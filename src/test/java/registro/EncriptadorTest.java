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

  @Test
  @DisplayName("Se comprueba que funciona el algoritmo de encriptamiento")
  public void contraseniaEncriptadaSHA256() throws NoSuchAlgorithmException {
    String hashCorrecto = "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
    String miHash = encriptador.encriptarContrasenia("hola","SHA-256");

    assertEquals(hashCorrecto, miHash);
  }
}
