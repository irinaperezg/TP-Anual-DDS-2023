package controllers;
import io.javalin.http.Context;
import models.domain.main.entidades.Entidad;
import models.domain.main.informes.rankings.CantidadIncidentesReportados;
import models.domain.main.informes.rankings.GradoImpactoProblematicas;
import models.domain.main.informes.rankings.PromedioCierre;
import models.domain.usuarios.Comunidad;
import models.domain.usuarios.Usuario;
import models.domain.usuarios.roles.TipoRol;
import models.indice.Menu;
import models.repositorios.*;
import server.utils.ICrudViewsHandler;
import services.RankingsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController extends Controller implements ICrudViewsHandler {

  private CantidadIncidentesReportados cantidadIncidentesReportados;
  private GradoImpactoProblematicas gradoImpactoProblematicas;
  private PromedioCierre promedioCierre;
  private EntidadRepository entidadRepository;
  private UsuarioRepository usuarioRepository;
  private RolRepository rolRepository;
  private MenuRepository menuRepository;
  private RankingsService rankingsService;

  public RankingsController(CantidadIncidentesReportados cantidadIncidentesReportados,
                            GradoImpactoProblematicas    gradoImpactoProblematicas,
                            PromedioCierre               promedioCierre,
                            EntidadRepository            entidadRepository,
                            MenuRepository               menuRepository,
                            UsuarioRepository            usuarioRepository,
                            RolRepository                rolRepository,
                            RankingsService              servicioRanking
  ) {
    this.promedioCierre               = promedioCierre;
    this.cantidadIncidentesReportados = cantidadIncidentesReportados;
    this.gradoImpactoProblematicas    = gradoImpactoProblematicas;
    this.entidadRepository            = entidadRepository;
    this.usuarioRepository            = usuarioRepository;
    this.rolRepository                = rolRepository;
    this.menuRepository               = menuRepository;
    this.rankingsService              = servicioRanking;
  }

  @Override
  public void index (Context context){
    // MENU
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Rankings")));
    Map<String, Object> model = new HashMap<>();
    model.put("menus", menus);
    //
    context.render("rankings.hbs", model);
  }

  @Override
  public void show (Context context) throws IOException {
    Map<String, Object> model = new HashMap<>();
    List<String> ranking = null;
    String descripcion = "";
    List<Entidad> entidades = this.entidadRepository.todos();
    String id = context.pathParam("id");

    switch(id) {
      case "1":
        ranking = this.rankingsService.pasarRankingAString(this.promedioCierre.elaborarRanking(entidades));
        descripcion = "Entidades con mayor promedio de tiempo de cierre de incidentes";
        break;
      case "2":
        ranking = this.rankingsService.pasarRankingAString(this.cantidadIncidentesReportados.elaborarRanking(entidades));
        descripcion = "Entidades con mayor cantidad de incidentes reportados en la semana";
        break;
      case "3":
        //ranking = this.rankingsService.pasarRankingAString(this.gradoImpactoProblematicas.elaborarRanking(entidades));
        ranking = new ArrayList<>();
        descripcion = "Incidentes con mayor grado de impacto de las problemáticas";
        break;
    }
    // MENU
    Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
    TipoRol tipoRol = this.rolRepository.buscarTipoRol(usuario.getRol().getId());
    List<Menu> menus = menuRepository.hacerListaMenu(tipoRol);
    menus.forEach(m -> m.setActivo(m.getNombre().equals("Rankings")));
    model.put("menus", menus);
    //
    model.put("ranking", ranking);
    model.put("id", id);
    model.put("descripcion", descripcion);

    // Imprime el ranking en la consola
    System.out.println("Ranking: " + ranking);
    context.result(entidades.get(0).getDenominacion());
    context.render("rankingPuntual.hbs", model);
  }

  // NADA
  public void create (Context context){
  }

  @Override
  public void save (Context context){
  }

  @Override
  public void edit (Context context){
  }

  @Override
  public void update (Context context){
  }

  @Override
  public void delete (Context context){
  }
}