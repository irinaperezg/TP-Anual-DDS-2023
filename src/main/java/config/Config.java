package config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
  static Path path = Paths.get("src/main/java/config/10k-worst-passwords.txt").toAbsolutePath();
  public static final String Archivo10kContrasenias = path.toString();
}
