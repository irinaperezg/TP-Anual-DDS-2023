package registro;

import excepciones.contrasenias.ExcepcionContraseniaInvalida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ValidezContraseniaTest {
  private Registro registro;
  private String nombre;
  private String contrasenia;

  @BeforeEach
  public void init() {
    this.registro = new Registro();
    this.nombre = "PepeArgento";
  }

  @Test
  @DisplayName("Se ingresa una contrasenia valida, debe pasar todos los tests")
  public void contraseniaValida(){
    contrasenia = "Validisima85$";

    assertTrue(registro.verificarValidez(nombre, contrasenia));
  }

  @Test
  @DisplayName("Se ingresa una contrasenia invalida, debe fallar")
  public void contraseniaInvalida(){
    contrasenia = "invalida";

    assertThrows(ExcepcionContraseniaInvalida.class,
        () -> registro.verificarValidez(nombre, contrasenia));
  }
}
