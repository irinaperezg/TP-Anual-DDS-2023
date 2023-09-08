package domain.main.servicio;
import domain.usuarios.Persona;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue("Servicio Compuesto")
public class ServicioCompuesto extends Servicio{
  @OneToMany
  private List<Servicio> servicios;
  public ServicioCompuesto(String descripcion, Servicio ... otrosServicios) {
    super(descripcion);
    agregarServicios(otrosServicios);
  }

  public ServicioCompuesto() {

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
