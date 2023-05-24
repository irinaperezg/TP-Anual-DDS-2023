package validadorDeContrasenias;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import shared.Shared;
import validadorDeContrasenias.encriptadores.Encriptador;
import validadorDeContrasenias.excepciones.ExcepcionContraseniaInvalida;
import validadorDeContrasenias.validaciones.Validacion;

public class ValidadorDeContrasenia {
  private static final List<Validacion> validaciones = new ArrayList<>();

  public ValidadorDeContrasenia() {
    this.agregarValidaciones();
  }

  public void agregarValidaciones() {
    String[] validacionesPorAgregar = new Shared().obtenerDelConfig("validaciones").split(",");

    for (String validacion : validacionesPorAgregar) {
      try {
        Class<? extends Validacion> clazz = Class.forName("validadorDeContrasenias.validaciones." + validacion).asSubclass(Validacion.class);
        Validacion instancia = clazz.getDeclaredConstructor().newInstance();
        validaciones.add(instancia);
      } catch (ClassNotFoundException | IllegalAccessException
               | InstantiationException | NoSuchMethodException
               | java.lang.reflect.InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  public boolean verificarValidez(String nombre, String contrasenia) throws ExcepcionContraseniaInvalida {
    return validaciones.stream().allMatch(validacion ->
        validacion.validarContrasenia(nombre, contrasenia));
  }

  public String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String hash = new Shared().obtenerDelConfig("hash");

    // Obtener la clase correspondiente al nombre del algoritmo de hash
    Class<? extends Encriptador> clazz = Class.forName("validadorDeContrasenias.encriptadores." + hash).asSubclass(Encriptador.class);
    // Crear una instancia de la clase
    Encriptador encriptador = clazz.getDeclaredConstructor().newInstance();

    return encriptador.encriptarContrasenia(contrasenia);
  }
}
