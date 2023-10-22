package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
import models.domain.main.localizacion.Provincia;
import models.domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import models.domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacionFactory;
import models.domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.IncidenteRepository;
import models.repositorios.LocalizacionRepository;
import models.repositorios.PersonaRepository;
import models.repositorios.UsuarioRepository;
import server.utils.ICrudViewsHandler;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class PersonasController extends Controller implements ICrudViewsHandler {
  private PersonaRepository personaRepository;
  private LocalizacionRepository localizacionRepository;

  public PersonasController(PersonaRepository personaRepository, LocalizacionRepository localizacionRepository) {
    this.personaRepository = personaRepository;
    this.localizacionRepository = localizacionRepository;
  }

  // VER PERFIL PROPIO
  @Override
  public void index(Context context) {
    String personaFrecuencia = "";
    try {
      Long usuarioId = context.sessionAttribute("usuario_id");
      if (usuarioId == null) {
        context.status(403).result("Acceso denegado");
        return;
      }

      Persona persona = this.personaRepository.buscarPorIDUsuario(usuarioId);
      if (persona == null) {
        context.status(404).result("Persona no encontrada");
        return;
      }
      Map<String, Object> model = new HashMap<>();


      if (persona.getFrecuenciaNotification().getClass().getSimpleName() == "NotificacionCuandoSucedeIncidente")
      {
        personaFrecuencia = "Cuando sucede";
      }
      else
      {
        personaFrecuencia = "Sin apuros";
      }

      String mensajeCambiosAplicados = context.queryParam("mensaje");
      model.put("mensajeCambiosAplicados", mensajeCambiosAplicados);
      model.put("persona", persona);
      model.put("personaFrecuencia", personaFrecuencia);
      context.render("perfil.hbs", model);
    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
  }

  // VER PERFIL DE PERSONA CON UN ID
  @Override
  public void show(Context context) {
    try {
      Long usuarioId = context.sessionAttribute("usuario_id");
      if (usuarioId == null) {
        context.status(403).result("Acceso denegado");
        return;
      }

      String id = context.pathParam("id");
      Persona persona = this.personaRepository.buscarPorID(Long.parseLong(id));
      if (persona == null) {
        context.status(404).result("Persona no encontrada");
        return;
      }
      Map<String, Object> model = new HashMap<>();
      model.put("persona", persona);
      context.render("perfilPersona.hbs", model);
    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
  }

  // EDITAR PERFIL PROPIO
  @Override
  public void edit(Context context) {
    try {
      Long usuarioId = context.sessionAttribute("usuario_id");
      if (usuarioId == null) {
        context.status(403).result("Acceso denegado");
        return;
      }

      Persona persona = this.personaRepository.buscarPorIDUsuario(usuarioId);
      if (persona == null) {
        context.status(404).result("Persona no encontrada");
        return;
      }

      // Obtener todas las localidades
      List<Localidad> localidades = this.localizacionRepository.todasLasLocalidades();
      List<Provincia> provincias = this.localizacionRepository.todasLasProvincias();

      Map<String, Object> model = new HashMap<>();
      model.put("persona", persona);
      model.put("provincias", provincias); // Añadir provincias al modelo
      model.put("localidades", localidades);  // Añadir localidades al modelo
      context.render("editarPerfil.hbs", model);
    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
  }


  public void update(Context context) {
    try {
      Long usuarioId = context.sessionAttribute("usuario_id");
      if (usuarioId == null) {
        context.status(403).result("Acceso denegado");
        return;
      }

      Persona persona = this.personaRepository.buscarPorIDUsuario(usuarioId);
      if (persona == null) {
        context.status(404).result("Persona no encontrada");
        return;
      }

      String nombre = context.formParam("nombre");
      String email = context.formParam("email");
      String telefono = context.formParam("telefono");
      String localidadString = context.formParam("localidad");

      String campo = context.formParam("campo");
      String valor = context.formParam("valor");


      EntityManager em = personaRepository.entityManager();
      EntityTransaction tx = em.getTransaction();

      try {
        tx.begin();

        if (nombre != null) {
          Usuario usuario = persona.getUsuario();
          usuario.setNombre(nombre);
          em.merge(usuario);
        }
        if (email != null) {
          persona.setEmail(email);
        }
        if (telefono != null) {
          persona.setTelefono(telefono);
        }
        if (localidadString != null) {
          Long localidadId = Long.parseLong(localidadString);
          Localidad localidad = new LocalizacionRepository().buscarLocalidadPorId(localidadId);
          if (localidad != null) {
            persona.setLocalidad(localidad);
          } else {
            context.status(404).result("Localidad no encontrada");
            return;
          }
        }
        switch (campo) {
          case "medioNotificacion":
            PreferenciaMedioNotificacion medio = PreferenciaMedioNotificacion.valueOf(valor);
            persona.setPreferenciaMedioNotificacion(medio);
            break;
          case "frecuenciaNotificacion":
          try {
            FrecuenciaNotificacion frecuencia = FrecuenciaNotificacionFactory.obtenerPorNombre(valor);
            persona.setFrecuenciaNotification(frecuencia);
            } catch (IllegalArgumentException e) {
                context.status(400).result("Frecuencia no reconocida");
                return;
            }
            break;
          case "horarios":
          String[] horariosArray = new Gson().fromJson(valor, String[].class);
          List<LocalDateTime> horariosList = new ArrayList<>();

          LocalDate currentDate = LocalDate.now();

          for (String horario : horariosArray) {
            try {
              LocalTime time = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
              LocalDateTime dateTime = LocalDateTime.of(currentDate, time).minusHours(3);;
              horariosList.add(dateTime);
            } catch (DateTimeParseException e) {
              // Manejar el error, por ejemplo, registrar o ignorar la fecha incorrecta.
            }
          }
          persona.setHorariosDeNotificaciones(horariosList);
          break;
          default:
          context.status(400).result("Campo no reconocido");
          return;
      }
        em.merge(persona);
        tx.commit();
      } catch (Exception e) {
        if (tx != null && tx.isActive()) tx.rollback();
        throw e;
      } finally {
        em.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
    context.result("Campo actualizado exitosamente");
    context.redirect("/perfil?mensaje=Campos+actualizados+exitosamente");
  }



  @Override
  public void delete(Context context) {
    //TODO
  }


  @Override
  public void save(Context context) {
    //TODO
  }



  @Override
  public void create(Context context) {
    //TODO
  }
}
