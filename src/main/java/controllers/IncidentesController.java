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
    List<Incidente> incidentes = this.incidenteRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("comunidades", comunidades);
    model.put("incidentes", incidentes);
    context.render("listarIncidente.hbs", model);
  }

  public void listarIncidentes(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    List<Comunidad> comunidades = this.comunidadRepository.buscarComunidadesUsuario(usuario);
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("comunidades", comunidades);

    Long comunidadId = Long.parseLong(context.pathParam("comunidadId"));
    String estado = context.pathParam("estado");

    Comunidad comunidadSeleccionada = comunidadRepository.buscarPorID(comunidadId);

    List<Incidente> incidentes;
    if (estado.equalsIgnoreCase("ambos")) {
      // Obtener todos los incidentes de la comunidad
      incidentes = incidenteRepository.buscarPorComunidad(comunidadId);
    } else {
      Boolean estadoBool = null;
      switch (estado) {
        case "abierto" -> estadoBool = true;
        case "cerrado" -> estadoBool = false;
      }
      incidentes = incidenteRepository.buscarPorComunidadYEstado(comunidadId, estadoBool);
    }

    model.put("comunidadSeleccionada", comunidadSeleccionada);
    model.put("incidentes", incidentes);

    // Renderizar la vista con los incidentes
    context.render("incidentes.hbs", model);
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
