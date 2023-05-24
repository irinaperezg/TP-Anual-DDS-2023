package shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Shared {
  private static final Properties prop = new Properties();

  public String obtenerDelConfig(String key) {
    try (InputStream input = Shared.class.getResourceAsStream("/config.properties")) {
      prop.load(input);
      return prop.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
