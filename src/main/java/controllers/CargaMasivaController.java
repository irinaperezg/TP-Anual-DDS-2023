package controllers;

import io.javalin.http.Context;
import models.repositorios.ComunidadRepository;
import models.repositorios.EntidadPrestadoraRepository;
import models.repositorios.OrganismoDeControlRepository;
import server.utils.ICrudViewsHandler;

public class CargaMasivaController implements ICrudViewsHandler {

  private EntidadPrestadoraRepository entidadPrestadoraRepository;
  private OrganismoDeControlRepository organismoDeControlRepository;

  public CargaMasivaController(EntidadPrestadoraRepository entidadPrestadoraRepository, OrganismoDeControlRepository organismoDeControlRepository) {
    this.entidadPrestadoraRepository = entidadPrestadoraRepository;
    this.organismoDeControlRepository = organismoDeControlRepository;
  }
  @Override
  public void index(Context context) {
    //TODO
  }

  @Override
  public void show(Context context) {
    //TODO
  }

  // basicamente llama al importador csv para q cree las entidades correspondientes
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