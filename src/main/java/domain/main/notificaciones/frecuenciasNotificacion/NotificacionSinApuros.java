package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.usuarios.Persona;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacionSinApuros {
  private List<Incidente> incidentesANotificar = new ArrayList<>();

  //TODO: INTENTAR UNIFICAR ESTOS DOS METODOS DE ACA ABAJO
  public void gestionarApertura(Persona persona, Incidente incidente) {
    incidentesANotificar.add(incidente);
  }

  public void gestionarCierre(Persona persona, Incidente incidente) {
    incidentesANotificar.add(incidente);
  }

  //TODO: CONTROLADOR DE TIEMPO(? QUE LLAME A ESTA FUNCION SEGUN SI SE ESTA EN EL HORARIO QUE ESTE ELIGIO
  public void notificarIncidente(Persona persona) {
    String mensaje = "";
    List<Incidente> incidentesRecientes = new ArrayList<>();
    incidentesRecientes = incidentesANotificar.stream().filter(incidente -> dentroDeLasUltimas24Horas(incidente)).toList();

    //for each incidente reciente --> crear un mensaje, notificarle todos a la persona

    persona.getPreferenciaMedioNotificacion().notificar(persona, mensaje);
  }

  private Boolean dentroDeLasUltimas24Horas(Incidente incidente) {
    LocalDateTime fechaCierre = incidente.getFechaCierre();
    if(fechaCierre != null) {
      return fechaCierre.isAfter(LocalDateTime.now().minusHours(24));
    }
    LocalDateTime fechaApertura = incidente.getFechaApertura();
    return fechaApertura.isAfter(LocalDateTime.now().minusHours(24));
  }
}
