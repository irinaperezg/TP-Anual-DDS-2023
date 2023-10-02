package controllers;

import io.javalin.http.Context;
import models.repositorios.PersonaRepository;
import server.utils.ICrudViewsHandler;

public class PersonasController implements ICrudViewsHandler {

  private PersonaRepository personaRepository;

  public PersonasController(PersonaRepository personaRepository) {
    this.personaRepository = personaRepository;
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
