package controllers;

import io.javalin.http.Context;
import models.domain.main.Establecimiento;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Miembro;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.*;
import server.utils.ICrudViewsHandler;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController implements ICrudViewsHandler {

  private ComunidadRepository comunidadRepository;
  private UsuarioRepository usuarioRepository;
  private MiembroRepository miembroRepository;
  private PersonaRepository personaRepository;
  private EstablecimientoRepository establecimientoRepository;
  private ServicioRepository servicioRepository;

  public ComunidadesController(ComunidadRepository comunidadRepository, UsuarioRepository usuarioRepository, MiembroRepository miembroRepository, PersonaRepository personaRepository, EstablecimientoRepository establecimientoRepository, ServicioRepository servicioRepository) {
    this.comunidadRepository = comunidadRepository;
    this.usuarioRepository = usuarioRepository;
    this.miembroRepository = miembroRepository;
    this.personaRepository = personaRepository;
    this.establecimientoRepository = establecimientoRepository;
    this.servicioRepository = servicioRepository;
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
    List<Comunidad> comunidades = this.comunidadRepository.todos(); // necesito del repositorio sacar todas las communidades
    Map<String, Object> modelo = new HashMap<>();


    for (Comunidad comunidad : comunidades) {
      cargarEstablecimientosEnComunidad(comunidad.getId());
      cargarServiciosEnComunidad(comunidad.getId());
    }

    modelo.put("comunidades", comunidades);
    context.render("sumarAComunidad.hbs", modelo);
  }


  @Override
  public void save(Context context) {
    Long comunidadId = Long.valueOf(context.formParam("comunidad_id"));
    String tipoMiembro = context.formParam("tipo"); // Esto te devuelve "observador" o "afectado".
    Long usuarioId = context.sessionAttribute("usuario_id");
    Persona persona = this.personaRepository.buscarPorIDUsuario(usuarioId);
    Comunidad comunidad = comunidadRepository.buscarPorID(comunidadId);

    // Deberías extender el método agregarPersonaAComunidad para que también acepte el tipo de miembro.
    Miembro miembro = comunidadRepository.agregarPersonaAComunidad(persona, comunidad, tipoMiembro);
    miembroRepository.registrar(miembro);
    context.redirect("/comunidades"); // Redirigir al listado de comunidades
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
  @Transactional
  public void cargarEstablecimientosEnComunidad(Long comunidadId) {
    Comunidad comunidad = comunidadRepository.buscarPorID(comunidadId); // Obtén la comunidad por su ID
    if (comunidad != null) {
      // Limpia la lista actual de establecimientos (si es necesario)
      comunidad.getEstablecimientosObservados().clear();

      // Obtén los establecimientos relacionados a través de la tabla intermedia
      List<Establecimiento> establecimientos = establecimientoRepository.obtenerEstablecimientosAsociados(comunidadId);

      // Agrega los establecimientos a la lista de la comunidad
      comunidad.getEstablecimientosObservados().addAll(establecimientos);

      // Guarda la comunidad actualizada en la base de datos
      comunidadRepository.actualizar(comunidad);
    }
  }

  @Transactional
  public void cargarServiciosEnComunidad(Long comunidadId) {
    Comunidad comunidad = comunidadRepository.buscarPorID(comunidadId); // Obtén la comunidad por su ID
    if (comunidad != null) {
      // Limpia la lista actual de establecimientos (si es necesario)
      comunidad.getServiciosObservados().clear();

      // Obtén los establecimientos relacionados a través de la tabla intermedia
      List<Servicio> servicios = servicioRepository.obtenerServiciosAsociados(comunidadId);

      // Agrega los establecimientos a la lista de la comunidad
      comunidad.getServiciosObservados().addAll(servicios);

      // Guarda la comunidad actualizada en la base de datos
      comunidadRepository.actualizar(comunidad);
    }
  }


}
