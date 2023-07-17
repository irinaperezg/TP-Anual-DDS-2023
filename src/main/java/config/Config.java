package config;

import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private static final Properties prop = new Properties();
  private static Config instancia;

  private Config() { //porque es un Singleton
  }

  public static Config obtenerInstancia() { // Singleton
    if (instancia == null){
      instancia = new Config();
    }
    return instancia;
  }

  public String obtenerDelConfig(String key) {
    try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
      prop.load(input);
      return prop.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
