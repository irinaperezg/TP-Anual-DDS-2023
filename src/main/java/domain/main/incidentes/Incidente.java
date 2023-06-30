package domain.main.incidentes;

import domain.main.PrestacionDeServicio;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

public class Incidente {
  private PrestacionDeServicio prestacion;
  private String observaciones;
  private LocalDateTime fechaApertura;
  private LocalDateTime fechaCierre;
  @Getter
  private Comunidad comunidad;
  @Setter @Getter
  private boolean abierto;
  private Miembro informante;

  public Incidente(LocalDateTime fechaApertura, String observaciones, Comunidad comunidad, PrestacionDeServicio prestacion, Miembro informante) {
    this.fechaApertura = fechaApertura;
    this.observaciones = observaciones;
    this.comunidad = comunidad;
    this.fechaCierre = null;
    this.abierto = true;
    this.prestacion = prestacion;
    this.informante = informante;
  }
}
