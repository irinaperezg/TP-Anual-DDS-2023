package domain.main.incidentes;

import domain.main.PrestacionDeServicio;
import domain.usuarios.Comunidad;
import domain.usuarios.Miembro;
import domain.usuarios.Persona;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

public class Incidente {
  private String observaciones;
  private PrestacionDeServicio prestacion;
  private LocalDateTime fechaApertura;
  @Setter
  private LocalDateTime fechaCierre;
  @Getter
  private Comunidad comunidad;
  @Setter @Getter
  private boolean abierto;

  public Incidente(String observaciones, Comunidad comunidad, PrestacionDeServicio prestacion) {
    this.fechaApertura = LocalDateTime.now();
    this.observaciones = observaciones;
    this.comunidad = comunidad;
    this.fechaCierre = null;
    this.abierto = true;
    this.prestacion = prestacion;
  }

  public void cerrar(){
    setFechaCierre(LocalDateTime.now());
    setAbierto(false);
  }
}
