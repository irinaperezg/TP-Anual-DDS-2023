package domain.main.informes;

import domain.main.entidades.Entidad;
import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.main.notificaciones.mediosNotificacion.Email.EmailAdapter;
import domain.main.notificaciones.mediosNotificacion.Email.JavaxMail;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.TwilioWpp;
import domain.main.notificaciones.mediosNotificacion.Whatsapp.WhatsappAdapter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rankeador {

  private static Rankeador instancia;

  private Rankeador() {
  }

  public static Rankeador obtenerInstancia() { // Singleton
    if (instancia == null){
      instancia = new Rankeador();
    }
    return instancia;
  }

  public List<String> elaborarRankingPromedioCierre(List<Entidad> entidades) {
    return entidades.stream()
        .sorted(Comparator.comparingLong(Entidad::obtenerPromedioCierreIncidentes).reversed())
        .toList().stream().map(Entidad::getDenominacion).toList();
  }

  public List<String> elaborarRankingCantidadIncidentesReportados(List<Entidad> entidades){
    return entidades.stream()
        .sorted(Comparator.comparingInt((Entidad entidad) -> entidad.obtenerIncidentesSemanales()
            .stream().filter(incidente -> !incidente.esRecienteYAbierto())
            .toList().size()).reversed()).toList().stream().map(Entidad::getDenominacion).toList();
  }

  public List<String> elaborarRankingGradoImpactoProblematicas(List<Incidente> incidentes){
    //TODO
    /*return incidentes.stream().sorted(Comparator.
        comparingInt(Incidente::calcularImpactoSobreComunidad)
        .reversed()).collect(Collectors.toList());*/
    return new ArrayList<>();
  }
}
