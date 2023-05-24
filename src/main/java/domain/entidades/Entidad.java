package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.localizacion.Localizacion;
import java.util.List;

public class Entidad {
  private String nombre;
  private TipoEntidad tipo;
  private List<Establecimiento> establecimientos;
  private Localizacion localizacion;
}