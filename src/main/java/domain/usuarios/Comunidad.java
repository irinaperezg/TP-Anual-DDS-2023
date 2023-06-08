package domain.usuarios;

import domain.main.Servicio;
import java.util.Arrays;
import java.util.List;

public class Comunidad {
  private List<Miembro> miembros;
  private List<Miembro> administradores;

  public List<Servicio> agregarAgrupacionDeServicios(Servicio ... servicios) {
    return Arrays.stream(servicios).toList();
  }
}
