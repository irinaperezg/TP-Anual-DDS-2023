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

      // TODO PERMISOS!!!!!!
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

      // VER COMUNIDAD PUNTUAL
      get("comunidades/{id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::show);

      // UNIRSE A COMUNIDAD
      post("comunidades/{id}", ((MiembrosController) FactoryController.controller("Miembros"))::create);

      // EDITAR EL TIPO DE MIEMBRO --> creo q el nombre de la ruta está mal, parece que fuese editar una comunidad pero
      // mi idea es q el miembro elija si es afectado u observador
      get("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::edit);
      post("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::update);

      // LISTAR INCIDENTES
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);

      // LISTAR INCIDENTES POR ESTADO
      get("incidentes/{estado}", ((IncidentesController) FactoryController.controller("Incidentes"))::listarPorEstado);

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

      // LISTAR ORGANISMOS DE CONTROL
      get("organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::index);

      // CARGA MASIVA
      get("cargaMasiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::index);
      post("cargaMasiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::save);

    });
  }
}