package controllers;


import models.domain.main.informes.rankings.CantidadIncidentesReportados;
import models.domain.main.informes.rankings.GradoImpactoProblematicas;
import models.domain.main.informes.rankings.PromedioCierre;
import models.repositorios.*;
import models.validadorDeContrasenias.ValidadorDeContrasenia;

public class FactoryController extends Controller{

  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new IncidenteRepository(), new UsuarioRepository(),
          new ComunidadRepository(), new ServicioRepository()); break;
      case "Comunidades": controller = new ComunidadesController(new ComunidadRepository(), new UsuarioRepository(), new MiembroRepository(), new PersonaRepository(), new EstablecimientoRepository(), new ServicioRepository()); break;
      case "Usuarios": controller = new UsuariosController(new UsuarioRepository(), new PersonaRepository(), new ValidadorDeContrasenia()); break;
      case "Signup": controller = new SignUpController(new UsuarioRepository(), new PersonaRepository(), new ValidadorDeContrasenia()); break;
      case "Login": controller = new LoginController(new UsuarioRepository(), new ValidadorDeContrasenia()); break;
      case "Miembros": controller = new MiembrosController(new MiembroRepository()); break;
      case "Personas": controller = new PersonasController(new PersonaRepository(), new LocalizacionRepository()); break;
      case "OrganismoDeControl": controller = new OrganismoDeControlController(new OrganismoDeControlRepository()); break;
      case "EntidadPrestadora": controller = new EntidadPrestadoraController(new EntidadPrestadoraRepository()); break;
      case "Rankings": controller = new RankingsController(new CantidadIncidentesReportados(), new GradoImpactoProblematicas(), new PromedioCierre(), new EntidadRepository()); break;
      case "CargaMasiva": controller = new CargaMasivaController(new EntidadPrestadoraRepository(), new OrganismoDeControlRepository()); break;

    }

    return controller;
  }
}