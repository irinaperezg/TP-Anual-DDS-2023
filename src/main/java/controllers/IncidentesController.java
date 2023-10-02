package controllers;

import models.repositorios.ComunidadRepository;
import models.repositorios.IncidenteRepository;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

public class IncidentesController implements ICrudViewsHandler {
  private IncidenteRepository incidenteRepository;

  public IncidentesController(IncidenteRepository incidenteRepository) {
    this.incidenteRepository = incidenteRepository;
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
