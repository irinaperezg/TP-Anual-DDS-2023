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
      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index, TipoRol.CONSUMIDOR);

      // BAJARSE DE UNA COMUNIDAD
      get("comunidades/baja/{comunidad_id}/{usuario_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::delete, TipoRol.CONSUMIDOR);


      // UNIRSE A COMUNIDAD
      get("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::add, TipoRol.CONSUMIDOR);
      post("comunidades/sumar", ((ComunidadesController) FactoryController.controller("Comunidades"))::save, TipoRol.CONSUMIDOR);



      // EDITAR EL TIPO DE MIEMBRO --> creo q el nombre de la ruta está mal, parece que fuese editar una comunidad pero
      // mi idea es q el miembro elija si es afectado u observador FALTA
      get("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::edit);
      post("comunidades/{id}/editar", ((MiembrosController) FactoryController.controller("Miembros"))::update);

      // LISTAR INCIDENTES
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index, TipoRol.CONSUMIDOR);

      // APERTURA DE INCIDENTE
      get("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create, TipoRol.CONSUMIDOR);
      post("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::save);

      // CIERRE DE INCIDENTE FALTA
      get("incidente/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
      post("incidente/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::update);

      // VER INCIDENTE PUNTUAL FALTA
      get("incidente/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);

      // REVISAR INCIDENTE
      //TODO

      // LISTAR ENTIDADES PRESTADORAS FALTA
      get("entidades-prestadoras", ((EntidadPrestadoraController) FactoryController.controller("EntidadPrestadora"))::index);

      // LISTAR ORGANISMOS DE CONTROL FALTA
      get("organismos-de-control", ((OrganismoDeControlController) FactoryController.controller("OrganismoDeControl"))::index);

      // CARGA MASIVA
      get("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::index, TipoRol.ADMINISTRADOR);
      post("carga-masiva", ((CargaMasivaController) FactoryController.controller("CargaMasiva"))::save);

      // RANKINGS
      get("rankings", ((RankingsController) FactoryController.controller("Rankings"))::index);
      get("rankings/{id}", ((RankingsController) FactoryController.controller("Rankings"))::show);


      //VER / ADMINISTRAR RECURSOS
      get("administrar", ((AdministrarController) FactoryController.controller("Administrar"))::index, TipoRol.ADMINISTRADOR);


      post("editar-comunidad", ((ComunidadesController) FactoryController.controller("Comunidades"))::edit, TipoRol.ADMINISTRADOR);
      get("editar-comunidad/eliminarServicio/{servicio_id}/{comunidad_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::deleteServicio, TipoRol.ADMINISTRADOR);
      get("editar-comunidad/eliminarEstablecimiento/{establecimiento_id}/{comunidad_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::deleteEstablecimiento, TipoRol.ADMINISTRADOR);

      get("todas-comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::show, TipoRol.ADMINISTRADOR);
      get("crear-comunidad", ((ComunidadesController) FactoryController.controller("Comunidades"))::create, TipoRol.ADMINISTRADOR);
      post("crear-comunidad", ((ComunidadesController) FactoryController.controller("Comunidades"))::guardar, TipoRol.ADMINISTRADOR);
      get("todas-comunidades/eliminarComunidad/{comunidad_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::deleteComunidad, TipoRol.ADMINISTRADOR);

      get("ver-comunidad/{comunidad_id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::ver, TipoRol.ADMINISTRADOR);

      get("todos-establecimientos", ((AdministrarController) FactoryController.controller("Administrar"))::indexEst, TipoRol.ADMINISTRADOR);
      get("crear-establecimiento", ((AdministrarController) FactoryController.controller("Administrar"))::crearEst, TipoRol.ADMINISTRADOR);
      post("crear-establecimiento", ((AdministrarController) FactoryController.controller("Administrar"))::guardarEst, TipoRol.ADMINISTRADOR);
      get("todos-establecimientos/eliminarEstablecimiento/{establecimiento_id}", ((AdministrarController) FactoryController.controller("Administrar"))::deleteEstablecimiento, TipoRol.ADMINISTRADOR);

      get("todas-entidades", ((AdministrarController) FactoryController.controller("Administrar"))::indexEnt, TipoRol.ADMINISTRADOR);
      get("crear-entidad", ((AdministrarController) FactoryController.controller("Administrar"))::crearEnt, TipoRol.ADMINISTRADOR);
      post("crear-entidad", ((AdministrarController) FactoryController.controller("Administrar"))::guardarEnt, TipoRol.ADMINISTRADOR);
      get("todas-entidades/eliminarEntidad/{entidad_id}", ((AdministrarController) FactoryController.controller("Administrar"))::deleteEntidad, TipoRol.ADMINISTRADOR);

      get("todas-prestaciones", ((AdministrarController) FactoryController.controller("Administrar"))::indexPrest, TipoRol.ADMINISTRADOR);
      get("crear-prestacion", ((AdministrarController) FactoryController.controller("Administrar"))::crearPrest, TipoRol.ADMINISTRADOR);
      post("crear-prestacion", ((AdministrarController) FactoryController.controller("Administrar"))::guardarPrest, TipoRol.ADMINISTRADOR);
      get("todas-prestaciones/eliminarPrestacion/{prestacion_id}", ((AdministrarController) FactoryController.controller("Administrar"))::deletePrestacion, TipoRol.ADMINISTRADOR);

    });
  }
}