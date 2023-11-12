package models.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class JsonEstablecimiento {
  private String denominacion;
  private String entidad;
  private String localidad;
  private List<Integer> prestaciones;
}
