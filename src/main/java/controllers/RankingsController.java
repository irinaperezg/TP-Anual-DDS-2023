package controllers;
import io.javalin.http.Context;
import models.domain.main.entidades.Entidad;
import models.domain.main.informes.rankings.CantidadIncidentesReportados;
import models.domain.main.informes.rankings.GradoImpactoProblematicas;
import models.domain.main.informes.rankings.PromedioCierre;
import models.domain.usuarios.Comunidad;
import models.repositorios.EntidadRepository;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController implements ICrudViewsHandler {

  private CantidadIncidentesReportados cantidadIncidentesReportados;
  private GradoImpactoProblematicas gradoImpactoProblematicas;
  private PromedioCierre promedioCierre;
  private EntidadRepository entidadRepository;

  public RankingsController(CantidadIncidentesReportados cantidadIncidentesReportados, GradoImpactoProblematicas gradoImpactoProblematicas,
                            PromedioCierre promedioCierre, EntidadRepository entidadRepository) {
    this.promedioCierre = promedioCierre;
    this.cantidadIncidentesReportados = cantidadIncidentesReportados;
    this.gradoImpactoProblematicas = gradoImpactoProblematicas;
    this.entidadRepository = entidadRepository;
  }

  @Override
  public void index (Context context){
    context.render("rankings.hbs");
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
        ranking = this.promedioCierre.elaborarRanking(entidades);
        descripcion = "Entidades con mayor promedio de tiempo de cierre de incidentes";
        break;
      case "2":
        ranking = this.cantidadIncidentesReportados.elaborarRanking(entidades);
        descripcion = "Entidades con mayor cantidad de incidentes reportados en la semana";
        break;
      case "3":
        ranking = this.gradoImpactoProblematicas.elaborarRanking(entidades);
        descripcion = "Incidentes con mayor grado de impacto de las problemáticas";
        break;
    }

    model.put("ranking", ranking);
    model.put("id", id);
    model.put("descripcion", descripcion);
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