package controllers;

import io.javalin.http.Context;
import models.domain.main.Establecimiento;
import models.domain.main.PrestacionDeServicio;
import models.domain.main.entidades.Entidad;
import models.domain.main.localizacion.Localidad;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Usuario;
import models.domain.usuarios.roles.TipoRol;
import models.indice.Menu;
import models.json.JsonEstablecimiento;
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

  public AdministrarController(UsuarioRepository usuarioRepository, MenuRepository menuRepository, RolRepository rolRepository, EstablecimientoRepository establecimientoRepository, PrestacionDeServicioRepository prestacionDeServicioRepository, LocalizacionRepository localizacionRepository, EntidadRepository entidadRepository) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
    this.menuRepository = menuRepository;
    this.establecimientoRepository = establecimientoRepository;
    this.prestacionDeServicioRepository = prestacionDeServicioRepository;
    this.localizacionRepository = localizacionRepository;
    this.entidadRepository = entidadRepository;
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
    List<PrestacionDeServicio> prestaciones = prestacionDeServicioRepository.todos();
    List<Localidad> localidades = localizacionRepository.todasLasLocalidades();
    List<Entidad> entidades = entidadRepository.todos();
    Map<String, Object> model = new HashMap<>();
    model.put("usuario", usuario);
    model.put("prestaciones", prestaciones);
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
    Long u= context.sessionAttribute("usuario_id");
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));

    if(usuario == null || !rolRepository.tienePermiso(usuario.getRol().getId(), "administrar_recursos")) {
      throw new AccessDeniedException();
    }

    JsonEstablecimiento data = context.bodyAsClass(JsonEstablecimiento.class);

    // Ahora 'data' contiene tus datos del JSON
    String denominacion = data.getDenominacion();
    String entidad = data.getEntidad();
    String localidad = data.getLocalidad();
    List<Integer> prestaciones = data.getPrestaciones();

    // Resto de tu lógica aquí

    // Respondes como sea necesario
    context.json(Map.of("mensaje", "Establecimiento creado exitosamente"));



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
}
