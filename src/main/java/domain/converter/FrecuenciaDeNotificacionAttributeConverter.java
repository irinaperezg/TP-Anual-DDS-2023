package domain.converter;

import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionCuandoSucedeIncidente;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionSinApuros;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class FrecuenciaDeNotificacionAttributeConverter implements AttributeConverter<FrecuenciaNotificacion, String> {
  @Override
  public String convertToDatabaseColumn(FrecuenciaNotificacion frecuenciaNotificacion) {
    String frecuencia = "";
    switch(frecuenciaNotificacion.getClass().getName()) {
      case "NotificacionCuandoSucedeIncidente": frecuencia = "Cuando sucede"; break;
      case "NotificacionSinApuros": frecuencia = "Sin apuros"; break;
    }
    return frecuencia;
  }

  @Override
  public FrecuenciaNotificacion convertToEntityAttribute(String frecuencia) {
    FrecuenciaNotificacion frecuenciaNotificacion = null;
    if(Objects.equals(frecuencia, "Cuando sucede"))
      frecuenciaNotificacion = new NotificacionCuandoSucedeIncidente();
    if(Objects.equals(frecuencia, "Sin apuros"))
      frecuenciaNotificacion = new NotificacionSinApuros();
    return frecuenciaNotificacion;
  }
}
