package controllers;

import io.javalin.http.Context;
import models.domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import models.domain.main.notificaciones.mediosNotificacion.PreferenciaMedioNotificacion;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.PersonaRepository;
import models.repositorios.UsuarioRepository;
import models.validadorDeContrasenias.ValidadorDeContrasenia;
import models.validadorDeContrasenias.encriptadores.Encriptador;
import models.validadorDeContrasenias.encriptadores.MD5;
import models.validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import org.quartz.SchedulerException;
import server.utils.ICrudViewsHandler;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import static com.github.jknack.handlebars.internal.lang3.BooleanUtils.toInteger;

public class UsuariosController implements ICrudViewsHandler {
  private UsuarioRepository usuarioRepository;
  private PersonaRepository personaRepository;
  private ValidadorDeContrasenia validadorDeContrasenia;

  public UsuariosController(UsuarioRepository usuarioRepository, PersonaRepository personaRepository, ValidadorDeContrasenia validadorDeContrasenia) {
    this.usuarioRepository = usuarioRepository;
    this.personaRepository = personaRepository;
    this.validadorDeContrasenia = validadorDeContrasenia;
  }

  @Override
  public void index(Context context) {
    //TODO DEVOLVER VISTA DE LOGIN
    //context.render(nombre de la vista de login);
  }

  public void validar(Context context) {
    String nombre = context.formParam("nombre");
    String contrasenia = context.formParam("contrasenia");
    String contraseniaEncriptadaDB = contraseniaEncriptadaDB = validadorDeContrasenia.encriptarContrasenia(contrasenia);
    Usuario usuario = this.usuarioRepository.buscarPorNombreUsuario(nombre);

    if (usuario.getContraseniaEncriptada() == contraseniaEncriptadaDB)
    {
      // TODO GARANTIZAR ACCESO

      // GUARDO EL ID DEL USUARIO EN LA SESION PARA PODER UTILIZARLA DESPUES
      context.sessionAttribute("id",usuario.getId());
    }
    else
    {
      // TODO CONTRASEÑA INVALIDA
    }

  }

  @Override
  public void show(Context context) {
    //TODO
  }

  public void create(Context context) {
    //TODO DEVOLVER VISTA DE SIGNUP
    //context.render(nombre de la vista de signup);
  }

  @Override
  public void save(Context context) {
    String nombre = context.formParam("nombre");
    String contrasenia = context.formParam("contrasenia");
    String telefono = context.formParam("telefono");
    String email = context.formParam("email");

    try {
      validadorDeContrasenia.verificarValidez(nombre, contrasenia);
    } catch (ExcepcionContraseniaInvalida e) {
      // TODO HAY Q VER SI NO ES VALIDA LA CONTRASEÑA Q INGRESA EL USUARIO
    }

    try {
      String contraseniaEncriptada = validadorDeContrasenia.encriptarContrasenia(contrasenia);
      Usuario usuario = new Usuario(nombre, contraseniaEncriptada);
      Persona persona = new Persona(usuario, email, telefono);
    } catch (NoSuchAlgorithmException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
      // TODO MANEJAR EL ERROR
    }
  }

  @Override
  public void edit(Context context) {
    //TODO creo q no hace falta se podria eliminar? porque la persona la editamos en persona controller.
  }

  @Override
  public void update(Context context) {
    //TODO creo q no hace falta se podria eliminar?
  }

  @Override
  public void delete(Context context) {
    //TODO creo q no hace falta se podria eliminar?
  }
}