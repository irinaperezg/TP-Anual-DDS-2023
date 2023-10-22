package controllers;

import io.javalin.http.Context;
import models.domain.main.Establecimiento;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.*;
import models.repositorios.*;
import server.exceptions.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComunidadesController extends Controller implements ICrudViewsHandler {

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
    Usuario usuarioLogueado = super.usuarioLogueado(context);

    if(usuarioLogueado == null || !usuarioLogueado.getRol().tienePermiso("crear_comunidad")) {
      throw new AccessDeniedException();
    }

    // Estas 4 lineas de código de arriba deberían estar en todos los métodos que quiera

    // DEVOLVER UNA VISTA QUE PERMITA CREAR UNA COMUNIDAD
    Map<String, Object> model = new HashMap<>();
    model.put("comunidad", null);
    context.render("crearComunidad.hbs", model);

  }


public void add (Context context) {
  Long usuarioId = context.sessionAttribute("usuario_id");
  Usuario usuario = usuarioRepository.buscarPorID(usuarioId);
  Persona persona = personaRepository.buscarPorIDUsuario(usuarioId);

  // Obtener todas las comunidades
  List<Comunidad> todasComunidades = comunidadRepository.todos();
  List<Comunidad> comunidadesSinMiembro = new ArrayList<>();
  // Cargar los establecimientos y servicios para todas las comunidades
  for (Comunidad comunidad : todasComunidades) {
    if (!miembroRepository.existePersonaEnComunidad(persona.getId(),comunidad.getId())){
      cargarEstablecimientosEnComunidad(comunidad.getId());
      cargarServiciosEnComunidad(comunidad.getId());
      comunidadesSinMiembro.add(comunidad);
    }

  }
    /*
    // Usando Java Stream API, filtrar las comunidades excluyendo las que ya tienen al miembro
    List<Comunidad> comunidadesSinMiembro = todasComunidades.stream()
        .filter(comunidad -> comunidad.getMiembros().stream()
            .noneMatch(miembro -> miembro.getPersona().equals(persona)))
        .collect(Collectors.toList());
*/
  // Convertir las comunidades en comunidadesView
  List<ComunidadView> comunidadesView = new ArrayList<>();

  for (Comunidad comunidad : comunidadesSinMiembro) {
    ComunidadView cv = new ComunidadView();
    cv.setId(comunidad.getId());
    cv.setNombre(comunidad.getNombre());

    String servicios = comunidad.getServiciosObservados()
        .stream()
        .map(servicio -> servicio.getDescripcion())
        .collect(Collectors.joining(", "));
    cv.setServicios(servicios);

    String establecimientos = comunidad.getEstablecimientosObservados()
        .stream()
        .map(establecimiento -> establecimiento.getDenominacion())
        .collect(Collectors.joining(", "));
    cv.setEstablecimientos(establecimientos);

    comunidadesView.add(cv);
  }

  Map<String, Object> modelo = new HashMap<>();
  modelo.put("comunidades", comunidadesView);
  modelo.put("usuario", usuario);
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
    Persona persona = personaRepository.buscarPorIDUsuario(usuarioId);
    Miembro miembro = miembroRepository.buscarMiembroPorPersonaId(persona.getId(), comunidadId);
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
