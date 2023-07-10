package domain.main;

import domain.localizacion.main.Localidad;
import domain.localizacion.main.Provincia;
import domain.localizacion.main.localizaciones.Municipio;
import domain.main.entidades.Entidad;
import domain.main.entidades.TipoEntidad;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionCuandoSucedeIncidente;
import domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import domain.main.servicio.Servicio;
import domain.main.servicio.ServicioBase;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class IncidentesTest {

  @Test
  @DisplayName("le llega una notificacion a los usuarios que comparten comunidad ante la apertura de un incidente")
  public void notificacion1() throws SchedulerException {
    Servicio servicio = new ServicioBase("baño sin genero");
    TipoEntidad tipoEntidad = new TipoEntidad();
    Entidad entidad = new Entidad(tipoEntidad,"entidad");
    Establecimiento establecimiento1 = new Establecimiento(entidad, "Banco Nacion");
    PrestacionDeServicio prestacion = new PrestacionDeServicio(establecimiento1,servicio);

    Localidad localidad1 = new Localidad(1,"fasfdasd", new Municipio(2,"asfa",new Provincia(3,"sfafdasf")));
    Localidad localidad2 = new Localidad(2,"fasfdfasfassd", new Municipio(3,"asffasfasda",new Provincia(4,"sfafdasfasfsaf")));

    Comunidad comunidad1 = new Comunidad();

    Usuario usuario = new Usuario("pepe","argento");
    Persona persona1 = new Persona(usuario,"compartoComunidad1@gmail.com","1234",new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro1 = new Miembro(persona1, comunidad1);

    Persona persona2 = new Persona(usuario, "compartoComunidad2@gmail.com","1234",new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro2 = new Miembro(persona2, comunidad1);

    Persona persona3 = new Persona(usuario, "noCompartoComunidad@gmail.com", "1234",new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    Miembro miembro3 = new Miembro(persona3, new Comunidad());

    comunidad1.agregarMiembro(miembro1);
    comunidad1.agregarMiembro(miembro2);
    establecimiento1.setLocalidad(localidad1);

    Persona persona4 = new Persona(usuario, "meInteresaElServicioPeroNoLaEntidad@gmail.com", "1234",new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    servicio.agregarAsociado(persona4);
    persona4.setLocalidad(localidad1);

    Persona persona5 = new Persona(usuario, "meInteresaLaEntidadPeroNoElServicio@gmail.com", "1234",new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    entidad.agregarAsociado(persona5);
    persona5.setLocalidad(localidad1);

    Persona persona6 = new Persona(usuario, "meInteresaLaEntidadYElServicio@gmail.com", "1234", new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.WHATSAPP, new ArrayList<>());
    entidad.agregarAsociado(persona6);
    servicio.agregarAsociado(persona6);
    persona6.setLocalidad(localidad1);

    Persona persona7 = new Persona(usuario, "meInteresaPeroEstaLejos@gmail.com", "1234", new NotificacionCuandoSucedeIncidente(), PreferenciaMedioNotificacion.EMAIL, new ArrayList<>());
    entidad.agregarAsociado(persona7);
    servicio.agregarAsociado(persona7);
    persona7.setLocalidad(localidad2);

    Notificador.obtenerInstancia(); //para que despues no sobreescriba el mock
    Notificador.setWhatsappAdapter(mock(WhatsappAdapter.class));

    doNothing().when(Notificador.getWhatsappAdapter()).mandar(anyString(), anyString());

    //prestacion.ocurrioUnIncidente(miembro1, "Se rompio el baño sin genero");
    //prestacion.cerrarUnIncidente(prestacion.getIncidentes().get(0));

    verify(Notificador.getWhatsappAdapter(), times(2)).mandar(anyString(), anyString());
  }
}
