package validadorDeContrasenias.validaciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import config.Config;
import validadorDeContrasenias.excepciones.ExcepcionComun;

public class NoEsComun implements Validacion {

  private static final List<String> contraseniasComunes = new ArrayList<>();

  public boolean validarContrasenia(String nombre, String contrasenia) throws ExcepcionComun {
    if (contraseniasComunes.contains(contrasenia)) {
      throw new ExcepcionComun("La contrasenia no debe ser una contrasenia comun");
    }
    return true;
  }

  public NoEsComun() {
    this.procesarArchivoDeContrasenasComunes();
  }

  public void procesarArchivoDeContrasenasComunes() {
    if (contraseniasComunes.isEmpty()) {

      String archivo10kContrasenias = Config.obtenerInstancia().obtenerDelConfig("archivo10kContrasenias");

      try {

        File file = new File(archivo10kContrasenias);
        Scanner myReader = new Scanner(file);

        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          contraseniasComunes.add(data);
        }

        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("No se pudo encontrar el archivo" + archivo10kContrasenias);
        e.printStackTrace();
      }
    }
  }
}