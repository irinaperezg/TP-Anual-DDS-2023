package domain.usuarios;

import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;
  private String email;
  private String telefono;

  public Usuario(String nombre, String contraseniaEncriptada, String email, String telefono) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
    this.email = email;
    this.telefono = telefono;
  }
}
