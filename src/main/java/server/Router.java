package server;

import controllers.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().routes(() -> {
      // LOGIN
      get("login", ((UsuariosController) FactoryController.controller("Usuarios"))::index);
      post("login", ((UsuariosController) FactoryController.controller("Usuarios"))::validar);

      // SIGN UP
      get("signup", ((UsuariosController) FactoryController.controller("Usuarios"))::create);
      post("signup", ((UsuariosController) FactoryController.controller("Usuarios"))::save);

      // CERRAR SESION
      get("logout", ((UsuariosController) FactoryController.controller("Usuarios"))::logout);


      // INICIO
      get("inicio", ((UsuariosController) FactoryController.controller("Usuarios"))::show);

      // PERFIL
      // TODO MAQUETA
      // VER PERFIL DE ALGUIEN
      get("perfil/{id}", ((PersonasController) FactoryController.controller("Personas"))::show);


      // EDITAR MI PERFIL (en figma se llama configuración)
      get("perfil", ((PersonasController) FactoryController.controller("Personas"))::edit);
      post("perfil", ((PersonasController) FactoryController.controller("Personas"))::update);

      // LISTAR COMUNIDADES
      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);

      // BAJARSE DE UNA COMUNIDAD
      get("comunidades/baja/{comunidad_id}/{usuario_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::delete);

      // CREAR NUEVA COMUNIDAD
      //get("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::create);
      post("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::save);

      // UNIRSE A COMUNIDAD
      get("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::create);

      // VER COMUNIDAD PUNTUAL
      get("comunidades/{id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::show);



      // EDITAR EL TIPO DE MIEMBRO --> creo q el nombre de la ruta está mal, parece que fuese editar una comunidad pero
      // mi idea es q el miembro elija si es afectado u observador
      get("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::edit);
      post("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::update);

      // LISTAR INCIDENTES
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      post("incidentes/{comunidadId}/{estado}", ((IncidentesController) FactoryController.controller("Incidentes"))::listarIncidentes);

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
      get("entidades-prestadoras", ((EntidadPrestadoraController) FactoryController.controller("EntidadPrestadora"))::index);

      // LISTAR ORGANISMOS DE CONTROL
      get("organismos-de-control", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::index);

      // CARGA MASIVA
      get("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::index);
      post("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::save);

    });
  }
}