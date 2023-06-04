package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeerArchivoCSVTest {
  private Shared shared;

  @BeforeEach
  public void init() {
    this.shared = new Shared();
  }

  @Test
  @DisplayName("Se lee el archivoCSV")
  public void leerArchivoCSV(){
    assertTrue(shared.leerArchivoCSV(shared.obtenerDelConfig("archivoCSV")));
  }
}
