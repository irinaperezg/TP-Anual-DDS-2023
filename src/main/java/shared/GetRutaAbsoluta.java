package shared;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GetRutaAbsoluta {
  public static String getRutaAbsoluta(String ruta) {
    Path path = Paths.get(ruta).toAbsolutePath();
    return path.toString();
  }
}
