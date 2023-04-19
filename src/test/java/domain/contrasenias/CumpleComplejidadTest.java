package domain.contrasenias;

import domain.excepciones.excepcionesContrasenias.ExcepcionComplejidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import domain.registro.Validaciones.CumpleRestriccionesNist;

public class CumpleComplejidadTest {
  private CumpleRestriccionesNist restriccionesNist;
  private String contrasenia;

  @BeforeEach
  public void init() {
    this.restriccionesNist = new CumpleRestriccionesNist();
  }

  @Test
  @DisplayName("Se ingresa una contrasenia compleja, debe aprobarse")
  public void contraseniaCompleja(){
    contrasenia = "Prueba123$";

    assertTrue(restriccionesNist.cumpleComplejidad(contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia sin numero, debe fallar")
  public void noTieneNumero(){
    contrasenia = "pruebA$";

    assertThrows(ExcepcionComplejidad.class,
        () -> restriccionesNist.cumpleComplejidad(contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia sin mayuscula, debe fallar")
  public void noTieneMayuscula(){
    contrasenia = "prueba1$";

    assertThrows(ExcepcionComplejidad.class,
        () -> restriccionesNist.cumpleComplejidad(contrasenia));
    }

  @Test
  @DisplayName("Se ingresa una contrasenia sin minuscula, debe fallar")
  public void noTieneMinuscula(){
    contrasenia = "PRUEBA1$";

    assertThrows(ExcepcionComplejidad.class,
        () -> restriccionesNist.cumpleComplejidad(contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia sin simbolo, debe fallar")
  public void noTieneSimbolo(){
    contrasenia = "pruebA123";

    assertThrows(ExcepcionComplejidad.class,
        () -> restriccionesNist.cumpleComplejidad(contrasenia));
  }
}
