package domain.main.servicio;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Servicio Base")
public class ServicioBase extends Servicio {
  public ServicioBase(String descripcion) {
    super(descripcion);
  }

  public ServicioBase() {

  }
}
