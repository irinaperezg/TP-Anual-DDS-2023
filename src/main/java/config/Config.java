package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private static final Properties prop = new Properties();

  public String obtenerDelConfig(String key) {
    try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
      prop.load(input);
      return prop.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
