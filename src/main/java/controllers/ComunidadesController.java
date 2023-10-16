package controllers;

import io.javalin.http.Context;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Usuario;
import models.repositorios.ComunidadRepository;
import models.repositorios.UsuarioRepository;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController implements ICrudViewsHandler {

  private ComunidadRepository comunidadRepository;
  private UsuarioRepository usuarioRepository;

  public ComunidadesController(ComunidadRepository comunidadRepository, UsuarioRepository usuarioRepository) {
    this.comunidadRepository = comunidadRepository;
    this.usuarioRepository = usuarioRepository;
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

  public void create(Context context) {
    context.render("suma.hbs");
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
    //TODO
  }

}
