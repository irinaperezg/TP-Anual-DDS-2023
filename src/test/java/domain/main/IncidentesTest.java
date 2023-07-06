package domain.main;

import domain.localizacion.main.Localidad;
import domain.localizacion.main.Provincia;
import domain.localizacion.main.localizaciones.Departamento;
import domain.main.entidades.Entidad;
import domain.main.servicio.Servicio;
import domain.usuarios.Miembro;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentesTest {
  private static Entidad entidad1;
  private static Provincia provincia;
  private static Servicio servicio1;
  private static PrestacionDeServicio prestacion1;
  private static PrestacionDeServicio prestacion2;
  private static PrestacionDeServicio prestacion3;

  @BeforeAll
  public static void init() {
    Usuario usuario = new Usuario("pepe", "argento");
    Miembro miembro = new Miembro(usuario);
    entidad1 = new Entidad(new Banco(),"Nacion");
    Establecimiento establecimiento1 = new Establecimiento(entidad1, "Belgrano");
    Entidad entidad2 = new Entidad(new Linea(), "Mitre");
    Establecimiento establecimiento2 = new Establecimiento(entidad2, "Retiro");
    provincia = new Provincia(1,"CABA");
    Departamento departamento1 = new Departamento(2,"Depto1", provincia);
    Localidad localidad = new Localidad(3, "Localidad1", departamento1);
    miembro.setLocalidad(localidad);
    establecimiento1.setLocalidad(localidad);
    establecimiento2.setLocalidad(localidad);
    servicio1 = new Servicio("Escalera Mecanica");
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
  @DisplayName("no le llega notificacion a ningun usuario")
  public void NotificarIncidenteServicioDeNoInteresPorLocalizacion(){
    Establecimiento establecimiento3 = new Establecimiento(entidad1, "Belgrano");
    Departamento departamento2 = new Departamento(4,"Depto2",provincia);
    Localidad localidad2 = new Localidad(5, "Localidad2",departamento2);
    establecimiento3.setLocalidad(localidad2);
    PrestacionDeServicio prestacion4 = new PrestacionDeServicio(establecimiento3, servicio1);

    prestacion4.ocurrioUnIncidente();
    //TODO (por ahora esta con println)
  }

  @Test
  @DisplayName("se muestra correctamente el tipo de entidad y tipo de establecimiento")
  public void TipoEntidad(){
    prestacion3.ocurrioUnIncidente();
    //TODO (por ahora esta con println)
  }
}
