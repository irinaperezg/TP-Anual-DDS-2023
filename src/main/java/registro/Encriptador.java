package registro;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {
  public String encriptarContrasenia(String contrasenia, String hash) throws NoSuchAlgorithmException {
    return switch (hash) {
      case "SHA-256" -> encriptarContraseniaSHA256(contrasenia);
      case "MD5" -> encriptarContraseniaMD5(contrasenia);
      default -> encriptarContraseniaSHA256(contrasenia);
    };
  }

  private String encriptarContraseniaSHA256(String contrasenia) {
    return Hashing.sha256()
        .hashString(contrasenia, StandardCharsets.UTF_8)
        .toString();
  }

  private String encriptarContraseniaMD5(String contrasenia) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(contrasenia.getBytes());

    return hashToString(md.digest());
  }

  private String hashToString(byte[] digest) {
    StringBuilder sb = new StringBuilder();
    for (byte b : digest) {
      String hex = String.format("%02x", b);
      sb.append(hex);
    }
    return sb.toString();
  }
}
