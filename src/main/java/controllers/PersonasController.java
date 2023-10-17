package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
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

public class PersonasController implements ICrudViewsHandler {
  private PersonaRepository personaRepository;


  public PersonasController(PersonaRepository personaRepository) {
    this.personaRepository = personaRepository;
  }

  @Override
  public void show(Context context) {
    String id = context.pathParam("id");
    Persona persona = this.personaRepository.buscarPorID(Long.parseLong(id));
    Map<String, Object> model = new HashMap<>();
    model.put("persona", persona);
    context.render("nombre de la vista para mostrar a la persona deseada", model); //TODO
  }

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
      LocalizacionRepository localizacionRepository = new LocalizacionRepository();
      List<Localidad> localidades = localizacionRepository.todasLasLocalidades();

      Map<String, Object> model = new HashMap<>();
      model.put("persona", persona);
      model.put("localidades", localidades);  // Añadir localidades al modelo
      context.render("perfil.hbs", model);
    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
  }




  @Override
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


      String campo = context.formParam("campo");
      String valor = context.formParam("valor");

      EntityManager em = personaRepository.entityManager();
      EntityTransaction tx = em.getTransaction();

      try {
        tx.begin();

        switch (campo) {
          case "nombre":
            Usuario usuario = persona.getUsuario();
            usuario.setNombre(valor);
            em.merge(usuario);
            break;
          case "email":
            persona.setEmail(valor);
            em.merge(persona);
            break;
          case "telefono":
            persona.setTelefono(valor);
            em.merge(persona);
            break;
          case "localidad":
            Long localidadId = Long.parseLong(valor);
            Localidad localidad = new LocalizacionRepository().buscarLocalidadPorId(localidadId);
            if (localidad != null) {
              persona.setLocalidad(localidad);
              em.merge(persona);
            } else {
              context.status(404).result("Localidad no encontrada");
              return;
            }
            break;
          case "medioNotificacion":
            PreferenciaMedioNotificacion medio = PreferenciaMedioNotificacion.valueOf(valor);
            persona.setPreferenciaMedioNotificacion(medio);
            em.merge(persona);
            break;
          case "frecuenciaNotificacion":
            try {
              FrecuenciaNotificacion frecuencia = FrecuenciaNotificacionFactory.obtenerPorNombre(valor);
              persona.setFrecuenciaNotification(frecuencia);
              em.merge(persona);
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
                LocalDateTime dateTime = LocalDateTime.of(currentDate, time);
                horariosList.add(dateTime);
              } catch (DateTimeParseException e) {
                // Manejar el error, por ejemplo, registrar o ignorar la fecha incorrecta.
              }
            }

            persona.setHorariosDeNotificaciones(horariosList);
            em.merge(persona);
            break;
          default:
            context.status(400).result("Campo no reconocido");
            return;
        }



        tx.commit();

      } catch (Exception e) {
        if (tx != null && tx.isActive()) tx.rollback();
        throw e;
      } finally {
        em.close();
      }

      context.result("Campo actualizado exitosamente");

    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result(e.getMessage());
    }
  }



  @Override
  public void delete(Context context) {
    //TODO
  }

  @Override
  public void index(Context context) {
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
