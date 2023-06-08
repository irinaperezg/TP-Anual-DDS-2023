package domain.main;

import domain.localizacion.Localizacion;
import domain.usuarios.Miembro;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentesTest {
  private static PrestacionDeServicio prestacion1;
  private static PrestacionDeServicio prestacion2;

  @BeforeAll
  public static void init() {
    Usuario usuario = new Usuario("pepe", "argento", "pepeargento@racing.com");
    Miembro miembro = new Miembro(usuario);
    Entidad entidad = new Entidad("Banco Nacion");
    Establecimiento establecimiento = new Establecimiento(entidad, "Sucursal Belgrano");
    Localizacion localizacion = new Localizacion();
    miembro.setLocalizacion(localizacion);
    establecimiento.setLocalizacion(localizacion);
    Servicio servicio1 = new Servicio("Escalera Mecanica");
    Servicio servicio2 = new Servicio("Ascensor");
    entidad.agregarMiembros(miembro);
    servicio1.agregarMiembros(miembro);
    prestacion1 = new PrestacionDeServicio(establecimiento, servicio1);
    prestacion2 = new PrestacionDeServicio(establecimiento, servicio2);
  }

  @Test
  @DisplayName("le llega una notificacion a los usuarios interesados")
  public void NotificarIncidenteServicioDeInteres(){
    prestacion1.ocurrioUnIncidente();
    //TODO (por ahora esta con println)
  }

  @Test
  @DisplayName("no le llega notificacion a ningun usuario")
  public void NotificarIncidenteServicioDeNoInteres(){
    prestacion2.ocurrioUnIncidente();
    //TODO (por ahora esta con println)
  }

  @Test
  @DisplayName("le llega una notificacion a los usuarios interesados")
  public void NotificarArregloServicioDeInteres(){
    prestacion1.seArregloElServicio();
    //TODO (por ahora esta con println)
  }

  @Test
  @DisplayName("no le llega notificacion a ningun usuario")
  public void NotificarArregloServicioDeNoInteres(){
    prestacion2.seArregloElServicio();
    //TODO (por ahora esta con println)
  }
}
