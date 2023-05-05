package validadorDeContrasenias;

import java.security.NoSuchAlgorithmException;

public interface Encriptador {
  String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException;
}
