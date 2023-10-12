package controllers;

import io.javalin.http.Context;
import models.domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import models.domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.IncidenteRepository;
import models.repositorios.PersonaRepository;
import server.utils.ICrudViewsHandler;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonasController implements ICrudViewsHandler {
  private PersonaRepository personaRepository;

  public PersonasController(PersonaRepository personaRepository) {
    this.personaRepository = personaRepository;
  }

  @Override
  public void show(Context context) {
    String id = context.pathParam("id");
    Persona persona = this.personaRepository.buscarPorID(Long.parseLong(id));
    Map<String, Object> model = new HashMap<>();
    model.put("persona", persona);
    context.render("nombre de la vista para mostrar a la persona deseada", model);
  }

  @Override
  public void edit(Context context) {
    Long id = context.sessionAttribute("id");
    Persona persona = this.personaRepository.buscarPorID(id);
    Map<String, Object> model = new HashMap<>();
    model.put("persona", persona);
    context.render("nombre de la vista para mostrar el perfil de la persona", model);
  }

  @Override
  public void update(Context context) {
    FrecuenciaNotificacion frecuenciaNotification;
    PreferenciaMedioNotificacion preferenciaMedioNotificacion;
    Long id = context.sessionAttribute("id");
    Persona persona = this.personaRepository.buscarPorID(id);

    // TODO HAY QUE VER CUALES COSAS EL USUARIO EDITA Y CUALES NO Y PERSISTIR LAS ACTUALIZACIONES
    // TODO TAMBIEN HAY QUE VER EL TEMA DE LAS LOCALIDADES
    // MEDIO DE NOTIFICACION REQUERIDO Y FRECUENCIA DE NOTIFICACION

    String nombre = context.formParam("nombre");
    String email = context.formParam("email");
    String telefono = context.formParam("telefono");
    String localidad = context.formParam("localidad");

  }

  @Override
  public void delete(Context context) {
    //TODO
  }

  @Override
  public void index(Context context) {
    //TODO
  }

  @Override
  public void save(Context context) {
    //TODO
  }

  @Override
  public void create(Context context) {
    //TODO
  }
}
