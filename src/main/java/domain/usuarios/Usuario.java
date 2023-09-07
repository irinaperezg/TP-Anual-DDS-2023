package domain.usuarios;

import domain.Persistente;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="usuario")
public class Usuario {

  @Id
  @GeneratedValue
  private Long id;

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
