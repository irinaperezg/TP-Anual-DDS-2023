package domain.usuarios;

import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.entidades.PrestacionDeServicio;
import domain.entidades.Servicio;
import domain.localizacion.Localizacion;
import domain.localizacion.Municipio;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InteresesTest {
  private Usuario usuario;
  private Entidad entidad;
  private Establecimiento establecimiento;
  private Municipio municipioCerca;
  private Municipio municipioLejos;
  private PrestacionDeServicio prestacion1;
  private PrestacionDeServicio prestacion2;
  private Localizacion localizacion1;


  @BeforeEach
  public void init() {
    this.usuario = new Usuario("pepe", "argento","pepeargento@racing");
    this.entidad = new Entidad("Banco Nacion");
    this.establecimiento = new Establecimiento("Banco Nacion Sucursal");
    this.municipioCerca = new Municipio(1,"La Matanza");
    this.municipioLejos = new Municipio(2,"Pilar");
    localizacion1 = new Localizacion();
    localizacion1.setMunicipio(municipioCerca);
    usuario.setLocalizacion(localizacion1);
    Servicio servicio1 = new Servicio("Escalera Mecanica");
    Servicio servicio2 = new Servicio("Ascensor");
    usuario.agregarServicios(servicio1);
    this.prestacion1 = new PrestacionDeServicio(servicio1);
    prestacion1.setDisponible(false);
    this.prestacion2 = new PrestacionDeServicio(servicio2);
    prestacion2.setDisponible(false);
  }

  @Test
  @DisplayName("al usuario le debe interesar la entidad porque esta cerca")
  public void leInteresaLaEntidadPorCercania(){
    localizacion1 = new Localizacion();
    localizacion1.setMunicipio(municipioCerca);
    establecimiento.setLocalizacion(localizacion1);
    entidad.agregarEstablecimientos(establecimiento);

    assertTrue(usuario.leInteresaUna(entidad));
  }

  @Test
  @DisplayName("al usuario no le debe interesar la entidad porque esta lejos")
  public void noLeInteresaLaEntidadPorCercania(){
    localizacion1 = new Localizacion();
    localizacion1.setMunicipio(municipioLejos);
    establecimiento.setLocalizacion(localizacion1);
    entidad.agregarEstablecimientos(establecimiento);

    assertFalse(usuario.leInteresaUna(entidad));
  }

  @Test
  @DisplayName("al usuario le debe interesar la entidad porque tiene un servicio de interes")
  public void leInteresaLaEntidadPorServicio(){
    establecimiento.agregarPrestacionDeServicios(prestacion1);
    entidad.agregarEstablecimientos(establecimiento);

    assertTrue(usuario.leInteresaUna(entidad));
  }

  @Test
  @DisplayName("al usuario no le debe interesar la entidad porque no tiene un servicio de interes")
  public void noLeInteresaLaEntidadPorServicio(){
    localizacion1 = new Localizacion();
    establecimiento.setLocalizacion(localizacion1);
    establecimiento.agregarPrestacionDeServicios(prestacion2);
    entidad.agregarEstablecimientos(establecimiento);

    assertFalse(usuario.leInteresaUna(entidad));
  }

  @Test
  @DisplayName("al usuario no le debe interesar la entidad porque el servicio de interes esta disponible")
  public void noLeInteresaLaEntidadPorServicioDisponible(){
    prestacion1.setDisponible(true);
    localizacion1 = new Localizacion();
    establecimiento.setLocalizacion(localizacion1);
    establecimiento.agregarPrestacionDeServicios(prestacion1);
    entidad.agregarEstablecimientos(establecimiento);

    assertFalse(usuario.leInteresaUna(entidad));
  }
}
