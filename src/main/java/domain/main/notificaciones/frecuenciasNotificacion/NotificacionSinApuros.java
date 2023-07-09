package domain.main.notificaciones.frecuenciasNotificacion;

import domain.main.incidentes.Incidente;
import domain.main.notificaciones.Notificador;
import domain.usuarios.Persona;
import org.apache.commons.lang3.tuple.Pair;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacionSinApuros implements FrecuenciaNotificacion {
  List<Pair<Incidente, Persona>> listaPares = new ArrayList<>();

  @Override
  public void gestionarInicidente(Persona persona, Incidente incidente) {
    Pair<Incidente, Persona> par = Pair.of(incidente, persona);
    listaPares.add(par);
  }

  public void notificarIncidentes() { // nadie sabe cuando se llama porque, por ahora, no lo atacamos ;)
    for (Pair<Incidente, Persona> par : listaPares) {
      Incidente incidente = par.getLeft();
      Persona persona = par.getRight();

      if (dentroDeLasUltimas24Horas(incidente)){
        String mensaje = incidente.generarMensaje();
        Notificador.obtenerInstancia().enviarNotificacion(persona, mensaje); // porque es un Singleton
      }
    }
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
