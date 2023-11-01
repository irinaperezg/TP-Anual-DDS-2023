package server;

import controllers.*;
import models.domain.usuarios.roles.TipoRol;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().routes(() -> {
      // LOGIN
      get("login", ((LoginController) FactoryController.controller("Login"))::index);
      post("login", ((LoginController) FactoryController.controller("Login"))::validar);

      // SIGN UP
      get("signup", ((SignUpController) FactoryController.controller("Signup"))::create);
      post("signup", ((SignUpController) FactoryController.controller("Signup"))::save);

      // CERRAR SESION
      get("logout", ((UsuariosController) FactoryController.controller("Usuarios"))::logout);

      // INICIO
      get("inicio", ((UsuariosController) FactoryController.controller("Usuarios"))::show);

      // PERFIL
      // VER MI PERFIL
      get("perfil", ((PersonasController) FactoryController.controller("Personas"))::index);
      // EDITAR MI PERFIL
      get("perfil/editar", ((PersonasController) FactoryController.controller("Personas"))::edit); // TODO CAMBIAR filtrado por PROVINCIAS Y LOCALIDADES
      post("perfil", ((PersonasController) FactoryController.controller("Personas"))::update);

      // VER PERFIL DE ALGUIEN
      get("perfil/{id}", ((PersonasController) FactoryController.controller("Personas"))::show);

      // LISTAR COMUNIDADES
      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);

      // BAJARSE DE UNA COMUNIDAD
      get("comunidades/baja/{comunidad_id}/{usuario_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::delete);

      // CREAR NUEVA COMUNIDAD
      get("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::create, TipoRol.ADMINISTRADOR);

      // UNIRSE A COMUNIDAD
      get("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::add);
      post("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::save);

      // VER COMUNIDAD PUNTUAL
      get("comunidades/{id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::show);



      // EDITAR EL TIPO DE MIEMBRO --> creo q el nombre de la ruta está mal, parece que fuese editar una comunidad pero
      // mi idea es q el miembro elija si es afectado u observador
      get("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::edit);
      post("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::update);

      // LISTAR INCIDENTES
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);

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

      // RANKINGS
      get("rankings", ((RankingsController) FactoryController.controller("Rankings"))::index);
      get("rankings/{id}", ((RankingsController) FactoryController.controller("Rankings"))::show);



    });
  }
}