package domain.main;

import domain.localizacion.Localizacion;
import domain.main.entidades.Entidad;
import domain.main.entidades.tipos.Banco;
import domain.main.entidades.tipos.Linea;
import domain.usuarios.Miembro;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentesTest {
  private static PrestacionDeServicio prestacion1;
  private static PrestacionDeServicio prestacion2;
  private static PrestacionDeServicio prestacion3;

  @BeforeAll
  public static void init() {
    Usuario usuario = new Usuario("pepe", "argento", "pepeargento@racing.com");
    Miembro miembro = new Miembro(usuario);
    Entidad entidad1 = new Entidad(new Banco(),"Nacion");
    Establecimiento establecimiento1 = new Establecimiento(entidad1, "Belgrano");
    Entidad entidad2 = new Entidad(new Linea(), "Mitre");
    Establecimiento establecimiento2 = new Establecimiento(entidad2, "Retiro");
    Localizacion localizacion = new Localizacion();
    miembro.setLocalizacion(localizacion);
    establecimiento1.setLocalizacion(localizacion);
    establecimiento2.setLocalizacion(localizacion);
    Servicio servicio1 = new Servicio("Escalera Mecanica");
    Servicio servicio2 = new Servicio("Ascensor");
    entidad1.agregarMiembros(miembro);
    entidad2.agregarMiembros(miembro);
    servicio1.agregarMiembros(miembro);
    prestacion1 = new PrestacionDeServicio(establecimiento1, servicio1);
    prestacion2 = new PrestacionDeServicio(establecimiento1, servicio2);
    prestacion3 = new PrestacionDeServicio(establecimiento2, servicio1);
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

  @Test
  @DisplayName("se muestra correctamente el tipo de entidad y tipo de establecimiento")
  public void TipoEntidad(){
    prestacion3.ocurrioUnIncidente();
    //TODO (por ahora esta con println)
  }
}
