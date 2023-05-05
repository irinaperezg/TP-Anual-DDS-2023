package config;

import validadorDeContrasenias.Validacion;

import java.util.ArrayList;
import java.util.List;

public class Config {
  public static final String Archivo10kContrasenias = "src/main/java/config/10k-worst-passwords.txt";
  public static String hash = "SHA-256";
  private final List<String> validaciones = new ArrayList<>();
}
