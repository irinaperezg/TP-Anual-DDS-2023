package models.domain.converter;

import models.domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import models.domain.main.notificaciones.frecuenciasNotificacion.NotificacionCuandoSucedeIncidente;
import models.domain.main.notificaciones.frecuenciasNotificacion.NotificacionSinApuros;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class FrecuenciaDeNotificacionAttributeConverter implements
    AttributeConverter<FrecuenciaNotificacion, String>
{
  @Override
  public String convertToDatabaseColumn(FrecuenciaNotificacion frecuenciaNotificacion) {
    if (frecuenciaNotificacion == null) {
      return null;  // o puedes retornar una cadena vacía o un valor predeterminado si lo prefieres.
    }
    String frecuencia = "";
    switch(frecuenciaNotificacion.getClass().getSimpleName()) {
      case "NotificacionCuandoSucedeIncidente": frecuencia = "Cuando sucede"; break;
      case "NotificacionSinApuros": frecuencia = "Sin apuros"; break;
      default: throw new IllegalArgumentException("Tipo de FrecuenciaNotificacion desconocido: " + frecuenciaNotificacion.getClass().getName());
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
