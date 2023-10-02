package server;

import controllers.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().routes(() -> {
      // LOGIN
      get("login", ((UsuariosController) FactoryController.controller("Usuarios"))::index);

      // SIGN UP
      get("singup", ((UsuariosController) FactoryController.controller("Usuarios"))::create);
      post("singup", ((UsuariosController) FactoryController.controller("Usuarios"))::save);

      // PERFIL (en figma se llama configuración)
      // VER PERFIL
      get("perfil/{id}", ((PersonasController) FactoryController.controller("Personas"))::show);
      // EDITAR PERFIL
      get("perfil/{id}/editar", ((PersonasController) FactoryController.controller("Personas"))::edit);

      // LISTAR COMUNIDADES
      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);

      // CREAR NUEVA COMUNIDAD
      get("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::create);
      post("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::save);

      // UNIRSE A COMUNIDAD
      // asumo q deberiamos crear una nueva comunidad y un nuevo tipo miembro
      //TODO

      // VER COMUNIDAD PUNTUAL
      //TODO

      // LISTAR INCIDENTES
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      // LISTAR INCIDENTES POR ESTADO
      //TODO
      //get("incidentes/{estado}", ((IncidentesController) FactoryController.controller("Incidentes"))::);

      // APERTURA DE INCIDENTE
      get("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
      post("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::save);

      // CIERRE DE INCIDENTE
      get("incidente/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
      post("incidente/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::update);

      // VER INCIDENTE PUNTUAL
      get("incidente/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);

      // REVISAR INCIDENTE
      //TODO

      // LISTAR ENTIDADES PRESTADORAS
      get("entidadesPrestadoras", ((EntidadPrestadoraController) FactoryController.controller("EntidadPrestadora"))::index);
      // CARGA MASIVA DE ENTIDADES PRESTADORAS
      get("entidadesPrestadoras/cargaMasiva", ((EntidadPrestadoraController) FactoryController.controller("EntidadPrestadora"))::indexCargaMasiva);
      post("entidadesPrestadoras/cargaMasiva", ((EntidadPrestadoraController) FactoryController.controller("EntidadPrestadora"))::saveCargaMasiva);

      // LISTAR ORGANISMOS DE CONTROL
      get("organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::index);

      // CARGA MASIVA DE ORGANISMOS DE CONTROL
      get("organismosDeControl/cargaMasiva", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::indexCargaMasiva);
      post("organismosDeControl/cargaMasiva", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::saveCargaMasiva);

    });
  }
}