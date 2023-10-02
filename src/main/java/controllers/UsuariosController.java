package controllers;

import models.repositorios.ComunidadRepository;
import models.repositorios.IncidenteRepository;
import io.javalin.http.Context;
import models.repositorios.UsuarioRepository;
import server.utils.ICrudViewsHandler;

public class UsuariosController implements ICrudViewsHandler {
  private UsuarioRepository usuarioRepository;

  public UsuariosController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public void index(Context context) {
    //TODO
  }

  @Override
  public void show(Context context) {
    //TODO
  }

  public void create(Context context) {
    //TODO
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
