package domain.usuarios;

import lombok.Getter;


@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;
  private String email;

  public Usuario(String nombre, String contraseniaEncriptada, String email) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
    this.email = email;
  }
}
