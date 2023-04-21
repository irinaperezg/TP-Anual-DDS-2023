package registro;

import registro.Validaciones.CumpleRestriccionesNist;
import excepciones.contrasenias.ExcepcionLongitud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CumpleLongitudTest {
  private CumpleRestriccionesNist restriccionesNist;
  private String contrasenia;

  @BeforeEach
  public void init() {
    this.restriccionesNist = new CumpleRestriccionesNist();
  }

  @Test
  @DisplayName("Se ingresa una contrasenia adecuada, debe aprobarse")
  public void contraseniaDeLongitudAdecuada() throws ExcepcionLongitud {
    contrasenia = "12345678910";

    assertTrue(restriccionesNist.cumpleLongitud(contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia muy corta, debe fallar")
  public void contraseniaCorta(){
    contrasenia = "12345";

    assertThrows(ExcepcionLongitud.class,
        () -> restriccionesNist.cumpleCaracteresMinima(contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia muy larga, debe fallar")
  public void contraseniaLarga(){
    contrasenia = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    assertThrows(ExcepcionLongitud.class,
        () -> restriccionesNist.cumpleCaracteresMaxima(contrasenia));
  }
}
