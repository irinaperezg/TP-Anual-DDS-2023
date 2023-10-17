package controllers;


import models.repositorios.*;
import models.validadorDeContrasenias.ValidadorDeContrasenia;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new IncidenteRepository(), new UsuarioRepository(),
          new ComunidadRepository(), new ServicioRepository()); break;
      case "Comunidades": controller = new ComunidadesController(new ComunidadRepository(), new UsuarioRepository(), new MiembroRepository(), new PersonaRepository()); break;
      case "Usuarios": controller = new UsuariosController(new UsuarioRepository(), new PersonaRepository(), new ValidadorDeContrasenia()); break;
      case "Miembros": controller = new MiembrosController(new MiembroRepository()); break;
      case "Personas": controller = new PersonasController(new PersonaRepository()); break;
      case "OrganismoDeControl": controller = new OrganismoDeControlController(new OrganismoDeControlRepository()); break;
      case "EntidadPrestadora": controller = new EntidadPrestadoraController(new EntidadPrestadoraRepository()); break;
      case "Rankings": controller = new RankingsController(); break;
      case "CargaMasiva": controller = new CargaMasivaController(new EntidadPrestadoraRepository(), new OrganismoDeControlRepository()); break;

    }

    return controller;
  }
}