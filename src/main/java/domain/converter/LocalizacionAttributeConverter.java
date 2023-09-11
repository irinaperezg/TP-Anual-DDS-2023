package domain.converter;
import domain.localizacion.main.Localizacion;
import domain.localizacion.main.localizaciones.Departamento;
import domain.localizacion.main.localizaciones.Municipio;
import domain.main.notificaciones.frecuenciasNotificacion.FrecuenciaNotificacion;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionCuandoSucedeIncidente;
import domain.main.notificaciones.frecuenciasNotificacion.NotificacionSinApuros;
import net.bytebuddy.asm.Advice;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class LocalizacionAttributeConverter implements AttributeConverter<Localizacion, String> {
  @Override
  public String convertToDatabaseColumn(Localizacion localizacion) {
    String localizacionString = "";
    switch(localizacion.getClass().getName()) {
      case "Departamento": localizacionString = "Departamento"; break;
      case "Municipio": localizacionString = "Municipio"; break;
    }
    return localizacionString;
  }

  @Override
  public Localizacion convertToEntityAttribute(String localizacionString) {
    Localizacion localizacion = null;
    if(Objects.equals(localizacionString, "Municipio"))
      localizacion = new Municipio();
    if(Objects.equals(localizacionString, "Departamento"))
      localizacion = new Departamento();
    return localizacion;
  }
}
