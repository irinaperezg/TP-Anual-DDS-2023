package controllers;

import io.javalin.http.Context;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Miembro;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.ComunidadRepository;
import models.repositorios.MiembroRepository;
import models.repositorios.PersonaRepository;
import models.repositorios.UsuarioRepository;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController implements ICrudViewsHandler {

  private ComunidadRepository comunidadRepository;
  private UsuarioRepository usuarioRepository;
  private MiembroRepository miembroRepository;
  private PersonaRepository personaRepository;

  public ComunidadesController(ComunidadRepository comunidadRepository, UsuarioRepository usuarioRepository, MiembroRepository miembroRepository, PersonaRepository personaRepository) {
    this.comunidadRepository = comunidadRepository;
    this.usuarioRepository = usuarioRepository;
    this.miembroRepository = miembroRepository;
    this.personaRepository = personaRepository;
  }
  @Override
  public void index(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    List<Comunidad> comunidades = this.comunidadRepository.buscarComunidadesUsuario(usuario);
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("comunidades", comunidades);
    context.render("listarComunidades.hbs", model);
  }



  @Override
  public void show(Context context) {
    //TODO
  }
  @Override
  public void create(Context context) {
    context.render("sumarAComunidad.hbs");
  }

  @Override
  public void save(Context context) {
    //TODO
  }

  @Override
  public void edit(Context context) {
    //TODO
  }

  @Override
  public void update(Context context) {
    //TODO
  }

  @Override
  public void delete(Context context) {
    Long comunidadId = Long.parseLong(context.pathParam("comunidad_id"));
    Long usuarioId = Long.parseLong(context.pathParam("usuario_id"));

    Comunidad comunidad = comunidadRepository.buscarPorID(comunidadId);
    Persona persona = personaRepository.buscarPorIDUsuario(usuarioId);
    Miembro miembro = miembroRepository.buscarMiembroPorPersonaId(persona.getId());
    miembroRepository.removeMiembro(miembro); // Implementa este método en la clase Comunidad
    context.redirect("/comunidades"); // Redirige a la lista de comunidades u otra página

  }


}
