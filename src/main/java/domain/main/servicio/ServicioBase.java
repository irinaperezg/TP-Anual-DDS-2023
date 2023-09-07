package domain.main.servicio;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "tipo")
public class ServicioBase extends Servicio {
  public ServicioBase(String descripcion) {
    super(descripcion);
  }

  public ServicioBase() {

  }
}
