package domain.main.servicio;
import domain.usuarios.Persona;
import java.util.Arrays;
import java.util.List;

public class ServicioCompuesto extends Servicio{
  private List<Servicio> servicios;
  public ServicioCompuesto(String descripcion, Servicio ... otrosServicios) {
    super(descripcion);
    agregarServicios(otrosServicios);
  }
  public void agregarServicios(Servicio ... serviciosNuevos){
    servicios.addAll(Arrays.stream(serviciosNuevos).toList());
  }
  public void eliminarServicios(Servicio ... serviciosASacar){
    servicios.removeAll(Arrays.stream(serviciosASacar).toList());
  }
  public boolean esDeInteresPara(Persona persona) {
    return servicios.stream().anyMatch(servicio -> servicio.esDeInteresPara(persona));
  }
}
