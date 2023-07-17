package domain.main.informes;

import config.Config;
import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import lombok.Getter;
import lombok.Setter;
import validadorDeContrasenias.validaciones.Validacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rankeador {

  private static Rankeador instancia;
  private static final List<Ranking> rankings = new ArrayList<>();

  private Rankeador() {
  }

  public static Rankeador obtenerInstancia() { // Singleton
    if (instancia == null){
      instancia = new Rankeador();
      instancia.agregarRankings();
    }
    return instancia;
  }

  public void agregarRankings() {
    String[] rankingsPorAgregar = Config.obtenerInstancia().obtenerDelConfig("rankings").split(",");

    for (String ranking : rankingsPorAgregar) {
      try {
        Class<? extends Ranking> clazz = Class.forName("rankeador.rankings." + ranking).asSubclass(Ranking.class);
        Ranking instancia = clazz.getDeclaredConstructor().newInstance();
        rankings.add(instancia);
      } catch (ClassNotFoundException | IllegalAccessException
               | InstantiationException | NoSuchMethodException
               | java.lang.reflect.InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
