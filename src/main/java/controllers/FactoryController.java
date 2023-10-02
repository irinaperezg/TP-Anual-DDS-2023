package controllers;


import models.repositorios.*;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new IncidenteRepository()); break;
      case "Comunidades": controller = new ComunidadesController(new ComunidadRepository()); break;
      case "Usuarios": controller = new UsuariosController(new UsuarioRepository()); break;
      case "Miembros": controller = new MiembrosController(new MiembroRepository()); break;
      case "Personas": controller = new PersonasController(new PersonaRepository()); break;
      case "OrganismoDeControl": controller = new OrganismoDeControlController(new OrganismoDeControlRepository()); break;
      case "EntidadPrestadora": controller = new EntidadPrestadoraController(new EntidadPrestadoraRepository()); break;
      case "Rankings": controller = new RankingsController(); break;

    }
    return controller;
  }
}