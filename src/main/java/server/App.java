package server;

import server.utils.Inicializador;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class App {

  public static void main(String[] args) throws IOException {
    String dbDriver = System.getenv("DB_DRIVER");
    String dbUrl = System.getenv("DB_URL");
    String dbUsername = System.getenv("DB_USERNAME");
    String dbPassword = System.getenv("DB_PASSWORD");
    String dbDialect = System.getenv("DB_DIALECT");

    System.out.println("DB_DRIVER: " + dbDriver);
    System.out.println("DB_URL: " + dbUrl);
    System.out.println("DB_USERNAME: " + dbUsername);
    System.out.println("DB_PASSWORD: " + dbPassword);
    System.out.println("DB_DIALECT: " + dbDialect);

    // Configura las propiedades usando las variables de entorno
    Map<String, String> properties = new HashMap<>();
    properties.put("hibernate.connection.driver_class", dbDriver);
    properties.put("hibernate.connection.url", dbUrl);
    properties.put("hibernate.connection.username", dbUsername);
    properties.put("hibernate.connection.password", dbPassword);
    properties.put("hibernate.dialect", dbDialect);

    // Crea el EntityManagerFactory con las propiedades configuradas
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-persistence-unit", properties);

    Server.init();
    Inicializador inicializador = new Inicializador();
    inicializador.inicializar();

    // Cierra el EntityManagerFactory cuando sea apropiado
    emf.close();
  }
}