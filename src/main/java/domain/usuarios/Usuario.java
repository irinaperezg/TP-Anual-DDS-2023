package domain.usuarios;

import domain.Persistente;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class Usuario {
  @Column(name="nombre", columnDefinition = "TEXT")
  private String nombre;

  @Column(name="contrasenia", columnDefinition = "TEXT")
  private String contraseniaEncriptada;

  public Usuario(String nombre, String contraseniaEncriptada) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
  }
  public Usuario() {

  }
}
