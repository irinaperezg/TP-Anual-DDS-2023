package controllers;

import io.javalin.http.Context;
import models.repositorios.ComunidadRepository;
import server.utils.ICrudViewsHandler;

public class ComunidadesController implements ICrudViewsHandler {

  private ComunidadRepository comunidadRepository;

  public ComunidadesController(ComunidadRepository comunidadRepository) {
    this.comunidadRepository = comunidadRepository;
  }
  @Override
  public void index(Context context) {
    context.render("listarComunidades.hbs");
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
