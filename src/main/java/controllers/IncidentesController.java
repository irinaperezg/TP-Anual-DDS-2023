package controllers;

import models.domain.main.PrestacionDeServicio;
import models.domain.main.incidentes.Incidente;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Usuario;
import models.repositorios.*;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController implements ICrudViewsHandler {
  private IncidenteRepository incidenteRepository;
  private UsuarioRepository usuarioRepository;
  private ComunidadRepository comunidadRepository;
  private ServicioRepository servicioRepository;

  public IncidentesController(IncidenteRepository incidenteRepository, UsuarioRepository usuarioRepository,
                              ComunidadRepository comunidadRepository, ServicioRepository servicioRepository) {
    this.incidenteRepository = incidenteRepository;
    this.usuarioRepository = usuarioRepository;
    this.comunidadRepository = comunidadRepository;
    this.servicioRepository = servicioRepository;
  }

  @Override
  public void index(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    List<Comunidad> comunidades = this.comunidadRepository.buscarComunidadesUsuario(usuario);
    //List<Incidente> incidentes = this.incidenteRepository.buscarPorComunidad(comunidad);
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("comunidades", comunidades);
    context.render("listarIncidentes.hbs", model);
  }

  public void listarPorEstado(Context context) {
    //TODO
  }


  @Override
  public void show(Context context) {
    //TODO
  }

  public void create(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    List<Comunidad> comunidades = this.comunidadRepository.buscarComunidadesUsuario(usuario);
    //List<PrestacionDeServicio> prestaciones = this.
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("comunidades", comunidades);
    //model.put("prestaciones", prestaciones);
    context.render("crearIncidente.hbs", model);
  }

  @Override
  public void save(Context context) {

    String prestacion = context.formParam("prestacion");
    String denominacion = context.formParam("denominacion");
    String observaciones = context.formParam("observaciones");
   // Incidente nuevoIncidente = new Incidente
   // this.incidenteRepository.registrar(incidente);
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
