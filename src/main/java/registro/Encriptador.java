package registro;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encriptador {
  String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException;
}
