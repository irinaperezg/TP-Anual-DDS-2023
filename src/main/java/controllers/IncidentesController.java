package controllers;

    import com.mysql.cj.protocol.x.CompressionAlgorithm;
    import models.domain.main.Establecimiento;
    import models.domain.main.PrestacionDeServicio;
    import models.domain.main.entidades.Entidad;
    import models.domain.main.incidentes.Incidente;
    import models.domain.main.servicio.Servicio;
    import models.domain.main.servicio.ServicioBase;
    import models.domain.usuarios.Comunidad;
    import models.domain.usuarios.Usuario;
    import models.repositorios.*;
    import io.javalin.http.Context;
    import net.bytebuddy.asm.Advice;
    import server.utils.ICrudViewsHandler;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

public class IncidentesController extends Controller implements ICrudViewsHandler {
  private IncidenteRepository incidenteRepository;
  private UsuarioRepository usuarioRepository;
  private ComunidadRepository comunidadRepository;
  private ServicioRepository servicioRepository;

  private PrestacionDeServicioRepository prestacionDeServicioRepository;

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
    List<Incidente> incidentes = new ArrayList<>();

    for (Comunidad comunidad : comunidades) {
        List<Incidente> incidentesComunidad = comunidad.getIncidentes();
        if (!incidentesComunidad.isEmpty()) {
            incidentes.addAll(incidentesComunidad);
        }
    }

    Map<String, Object> model = new HashMap<>();

    model.put("comunidades", comunidades);
    model.put("incidentes", incidentes);

    context.render("listarIncidentes.hbs", model);
  }

  @Override
  public void show(Context context) {
    //TODO
  }

  @Override
  public void create(Context context) {
      Usuario usuario = this.usuarioRepository.buscarPorID(context.sessionAttribute("usuario_id"));
      List<Comunidad> comunidades = this.comunidadRepository.buscarComunidadesUsuario(usuario);
      List<PrestacionDeServicio> prestaciones = new ArrayList<>();

      for(Comunidad comunidad: comunidades) {
          for(Establecimiento establecimiento : comunidad.getEstablecimientosObservados()) {
              prestaciones.addAll(establecimiento.getPrestaciones());
          }
      }

      Map<String, Object> model = new HashMap<>();
      model.put("comunidades", comunidades);
      model.put("prestaciones", prestaciones); //TODO meter esto en hbs y ver como referenciar la comunidad

        context.render("crearIncidente.hbs", model);
  }

  @Override
  public void save(Context context) {
    String prestacion_id = context.formParam("prestacion_id");
    String incidente_denominacion = context.formParam("incidente_denominacion");
    String incidente_observaciones = context.formParam("incidente_observaciones");

    Incidente nuevoIncidente = new Incidente();
    nuevoIncidente.setCreador(context.sessionAttribute("usuario_id"));
    nuevoIncidente.setPrestacion(prestacionDeServicioRepository.buscarPorID(Long.parseLong(prestacion_id)));
    nuevoIncidente.setDenominacion(incidente_denominacion);
    nuevoIncidente.setObservaciones(incidente_observaciones);
    nuevoIncidente.setFechaApertura(LocalDateTime.now());
    nuevoIncidente.setFechaCierre(null);
    nuevoIncidente.setAbierto(true);

    this.incidenteRepository.registrar(nuevoIncidente);
  }

  @Override
  public void edit(Context context) {
    //TODO
  }

  @Override
  public void update(Context context) {
    String id = context.pathParam("id");
    this.incidenteRepository.cerrarIncidente(Long.parseLong(id));
    context.redirect("../../incidentes");
  }

  @Override
  public void delete(Context context) {
    //TODO
  }

}
