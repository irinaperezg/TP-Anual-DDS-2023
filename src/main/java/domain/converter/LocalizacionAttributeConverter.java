package domain.converter;
import domain.localizacion.main.Localizacion;
import domain.localizacion.main.TipoLocalizacion;

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
    {
      localizacion = new Localizacion();
      localizacion.setTipoLocalizacion(TipoLocalizacion.Municipio);
    }
    if(Objects.equals(localizacionString, "Departamento"))
    {
      localizacion = new Localizacion();
      localizacion.setTipoLocalizacion(TipoLocalizacion.Departamento);
    }
    return localizacion;
  }
}
