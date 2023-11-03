package controllers;

import io.javalin.http.Context;
import models.domain.usuarios.Usuario;
import models.domain.usuarios.roles.TipoRol;
import models.indice.Menu;
import models.repositorios.*;
import models.validadorDeContrasenias.ValidadorDeContrasenia;
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

  public AdministrarController(UsuarioRepository usuarioRepository, MenuRepository menuRepository, RolRepository rolRepository) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
    this.menuRepository = menuRepository;
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
}
