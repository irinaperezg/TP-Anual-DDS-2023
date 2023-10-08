package models.domain.usuarios;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="nombre", columnDefinition = "TEXT", unique = true)
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
