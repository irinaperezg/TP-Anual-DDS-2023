package controllers;

import io.javalin.http.Context;
import models.domain.usuarios.Persona;
import models.domain.usuarios.Usuario;
import models.repositorios.PersonaRepository;
import models.repositorios.UsuarioRepository;
import models.validadorDeContrasenias.ValidadorDeContrasenia;
import models.validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import server.utils.ICrudViewsHandler;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

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


  //LOGIN
  @Override
  public void index(Context context) {
    context.render("login.hbs");
  }

  public void validar(Context context) {
    String nombre = context.formParam("nombre");
    String contrasenia = context.formParam("contrasenia");
    String contraseniaEncriptadaDB = " ";
    Usuario usuario = this.usuarioRepository.buscarPorNombreUsuario(nombre);

    try {
      contraseniaEncriptadaDB = this.validadorDeContrasenia.encriptarContrasenia(contrasenia);
    } catch (NoSuchAlgorithmException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
      // TODO MANEJAR EL ERROR no se pudo encriptar
    }

    if (usuario.getContraseniaEncriptada().equals(contraseniaEncriptadaDB))
    {
      // GUARDO EL ID DEL USUARIO EN LA SESION PARA PODER UTILIZARLA DESPUES
      context.sessionAttribute("usuario_id", usuario.getId());

      // REDIGIR
      context.redirect("/inicio");
    }
    else
    {
      // TODO CONTRASEÑA INVALIDA
    }

  }

  // INICIO
  @Override
  public void show(Context context) {
    //TODO agarrar el nombre del usuario y que en inicio.hbs muestre Bienvenido/a NOMBRE
    context.render("inicio.hbs");
  }

  //SIGNUP
  @Override
  public void create(Context context) {
    context.render("signup.hbs");
  }

  @Override
  public void save(Context context) {
    String nombre = context.formParam("nombre");
    String contrasenia = context.formParam("contrasenia");
    String telefonoYMail = context.formParam("telefonoYMail");
    String tipoSeleccionado = context.formParam("tipoSeleccionado");
    String contraseniaEncriptada = " ";

    try {
      validadorDeContrasenia.verificarValidez(nombre, contrasenia);
    } catch (ExcepcionContraseniaInvalida e) {
      // TODO HAY Q VER SI NO ES VALIDA LA CONTRASEÑA Q INGRESA EL USUARIO
    }

    try {
      contraseniaEncriptada = validadorDeContrasenia.encriptarContrasenia(contrasenia);
      Usuario usuario = new Usuario(nombre, contraseniaEncriptada);
      usuarioRepository.registrar(usuario);

      Persona persona;
      if (tipoSeleccionado.equals("correo")) {
        persona = new Persona(usuario, telefonoYMail, "");
      } else {
        persona = new Persona(usuario, "", telefonoYMail);
      }

      //personaRepository.registrar(persona); TODO si persisto la persona rompe

      context.redirect("/login");
    } catch (NoSuchAlgorithmException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {


      //TODO hacer algo con la excepción
      e.printStackTrace();
    }
  }


  //NADA
  @Override
  public void edit(Context context) {}

  @Override
  public void update(Context context) {}

  @Override
  public void delete(Context context) {}
}
