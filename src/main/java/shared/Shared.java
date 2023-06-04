package shared;

import domain.entidades.EntidadPrestadora;
import domain.entidades.OrganismoDeControl;
import domain.usuarios.Delegado;
import domain.usuarios.Usuario;
import shared.excepciones.ExcepcionTipoNoExistente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Shared {
  private static final Properties prop = new Properties();

  public String obtenerDelConfig(String key) {
    try (InputStream input = Shared.class.getResourceAsStream("/config.properties")) {
      prop.load(input);
      return prop.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean leerArchivoCSV(String archivoCSV) {
    try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
      String linea;
      boolean primeraLinea = true;

      while ((linea = br.readLine()) != null) {
        if (primeraLinea) {
          primeraLinea = false;
          continue;
        }

        procesarLineaCSV(linea);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private void procesarLineaCSV(String linea) {
    String[] datos = linea.split(";");

    Delegado delegado = new Delegado(datos[2], datos[3]);

    if (datos[1].equals("Organismo de control")) {
      OrganismoDeControl organismo = new OrganismoDeControl(datos[0],delegado);

      //System.out.println("Organismo de control: " + organismo + "\nNombre: " + organismo.getDenominacion());
      //TODO subir a base de datos el organismo
    }
    else if (datos[1].equals("Entidad prestadora")) {
      EntidadPrestadora entidadPrestadora = new EntidadPrestadora(datos[0],delegado);

      //System.out.println("Entidad prestadora: " + entidadPrestadora + "\nNombre: " + entidadPrestadora.getDenominacion());
      //TODO subir a base de datos la entidad
    }
    else {
      throw new ExcepcionTipoNoExistente("Tipo no reconocido");
    }
  }
}
