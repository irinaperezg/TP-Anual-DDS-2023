package controllers;

import io.javalin.http.Context;
import models.domain.main.EntidadPrestadora;
import models.domain.main.Establecimiento;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.entidades.Entidad;
import models.domain.main.entidades.TipoEntidad;
import models.domain.main.localizacion.Localidad;
import models.domain.main.servicio.Servicio;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Miembro;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.domain.usuarios.roles.TipoRol;
import models.indice.Menu;
import models.json.JsonEntidad;
import models.json.JsonEstablecimiento;
import models.json.JsonPrestacion;
import models.repositorios.*;
import models.validadorDeContrasenias.ValidadorDeContrasenia;
import org.jetbrains.annotations.NotNull;
import server.exceptions.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrarController  extends Controller implements ICrudViewsHandler {
  private UsuarioRepository usuarioRepository;
  private MenuRepository menuRepository;
  private RolRepository rolRepository;
  private EstablecimientoRepository establecimientoRepository;
  private PrestacionDeServicioRepository prestacionDeServicioRepository;
  private LocalizacionRepository localizacionRepository;
  private EntidadRepository entidadRepository;
  private EntidadPrestadoraRepository entidadPrestadoraRepository;
  private ServicioRepository servicioRepository;

  public AdministrarController(UsuarioRepository usuarioRepository, MenuRepository menuRepository, RolRepository rolRepository, EstablecimientoRepository establecimientoRepository, PrestacionDeServicioRepository prestacionDeServicioRepository, LocalizacionRepository localizacionRepository, EntidadRepository entidadRepository, EntidadPrestadoraRepository entidadPrestadoraRepository, ServicioRepository servicioRepository) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
    this.menuRepository = menuRepository;
    this.establecimientoRepository = establecimientoRepository;
    this.prestacionDeServicioRepository = prestacionDeServicioRepository;
    this.localizacionRepository = localizacionRepository;
    this.entidadRepository = entidadRepository;
    this.entidadPrestadoraRepository = entidadPrestadoraRepository;
    this.servicioRepository = servicioRepository;
  }

  @Override
  public void index (Context context){
    Long usuarioId = context.sessionAttribute("usuario_id");
    Usuario usuario = usuarioRepository.buscarPorID(usuarioId);

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    Map<String, Object> model = new HashMap<>();
    model.put("menus", menus);
    //
    context.render("administrar.hbs", model);
  }

  @Override
  public void show(Context context) throws IOException {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }

  public void indexEst( Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }
    List<Establecimiento> establecimientos = establecimientoRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("establecimientos", establecimientos);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("listarEstablecimientos.hbs", model);
  }

  public void crearEst(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }
    List<Localidad> localidades = localizacionRepository.todasLasLocalidades();
    List<Entidad> entidades = entidadRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("localidades", localidades);
    model.put("entidades", entidades);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("crearEstablecimiento.hbs", model);
  }

  public void guardarEst( Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    JsonEstablecimiento data = context.bodyAsClass(JsonEstablecimiento.class);

    // Ahora 'data' contiene tus datos del JSON
    String denominacion = data.getDenominacion();
    Long entidad_id = data.getEntidad();
    Long localidad_id = data.getLocalidad();

    Localidad localidad = localizacionRepository.buscarLocalidadPorId(localidad_id);
    Entidad entidad = entidadRepository.buscarPorID(entidad_id);

    Establecimiento establecimiento = new Establecimiento(denominacion, entidad, localidad);
    establecimientoRepository.registrar(establecimiento);

    Map<String, String> respuesta = new HashMap<>();

    respuesta.put("mensaje", "Establecimiento guardado exitosamente");
    context.json(respuesta);
  }

  public void deleteEstablecimiento(Context context) {
    Long establecimiento_id = Long.parseLong(context.pathParam("establecimiento_id"));
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    Establecimiento establecimiento = establecimientoRepository.buscarPorID(establecimiento_id);
    establecimientoRepository.remove(establecimiento);
    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    List<Establecimiento> establecimientos = establecimientoRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("establecimientos", establecimientos);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.redirect("/todos-establecimientos");

  }

  public void indexEnt(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }
    List<Entidad> entidades = entidadRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("entidades", entidades);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("listarEntidades.hbs", model);
  }

  public void crearEnt(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    List<EntidadPrestadora> entidadesPrestadoras = entidadPrestadoraRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("entidadesPrestadoras", entidadesPrestadoras);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("crearEntidad.hbs", model);
  }

  public void guardarEnt( Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    JsonEntidad data = context.bodyAsClass(JsonEntidad.class);

    String denominacion = data.getDenominacion() ;
    String tipoEntidad = data.getTipoEntidad() ;
    String tipoEstablecimiento = data.getTipoEstablecimiento();
    Long entidadPrestadoraId = data.getEntidadPrestadoraId();


    EntidadPrestadora entidadPrestadora = entidadPrestadoraRepository.buscarPorID(entidadPrestadoraId);
    TipoEntidad tipoEntidadClase = new TipoEntidad(tipoEntidad, tipoEstablecimiento);

    Entidad entidad = new Entidad(tipoEntidadClase, denominacion, entidadPrestadora);
    entidadRepository.registrar(entidad);

    Map<String, String> respuesta = new HashMap<>();

    respuesta.put("mensaje", "Entidad guardada exitosamente");
    context.json(respuesta);
  }

  public void deleteEntidad(Context context) {

    Long entidad_id = Long.parseLong(context.pathParam("entidad_id"));
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    Entidad entidad = entidadRepository.buscarPorID(entidad_id);
    entidadRepository.remove(entidad);
    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    List<Entidad> entidades = entidadRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("entidades", entidades);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.redirect("/todas-entidades");
  }

  public void indexPrest(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }
    List<PrestacionDeServicio> prestacionesDeServicios = prestacionDeServicioRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("prestacionesDeServicios", prestacionesDeServicios);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("listarPrestaciones.hbs", model);
  }

  public void crearPrest(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    List<Establecimiento> establecimientos = establecimientoRepository.todos();
    List<Servicio> servicios = servicioRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("establecimientos", establecimientos);
    model.put("servicios", servicios);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.render("crearPrestacion.hbs", model);
  }

  public void guardarPrest(Context context) {
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    JsonPrestacion data = context.bodyAsClass(JsonPrestacion.class);

    Long establecimientoId = data.getEstablecimiento() ;
    Establecimiento establecimiento = establecimientoRepository.buscarPorID(establecimientoId);
    Long servicioId = data.getServicio();
    Servicio servicio = servicioRepository.buscarPorID(servicioId);
    PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(establecimiento, servicio);

    prestacionDeServicioRepository.registrar(prestacionDeServicio);

    Map<String, String> respuesta = new HashMap<>();

    respuesta.put("mensaje", "Prestación de Servicio guardada exitosamente");
    context.json(respuesta);
  }

  public void deletePrestacion(Context context) {
    Long prestacion_id = Long.parseLong(context.pathParam("prestacion_id"));
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    PrestacionDeServicio prestacionDeServicio = prestacionDeServicioRepository.buscarPorID(prestacion_id);
    prestacionDeServicioRepository.remove(prestacionDeServicio);
    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    List<PrestacionDeServicio> prestacionDeServicios = prestacionDeServicioRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("prestaciones", prestacionDeServicios);
    // MENU
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Administrar")));
    model.put("menus", menus);
    //
    context.redirect("/todas-prestaciones");
  }
}
