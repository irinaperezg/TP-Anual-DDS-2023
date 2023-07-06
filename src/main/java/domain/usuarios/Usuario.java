package domain.usuarios;

import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;


  public Usuario(String nombre, String contraseniaEncriptada) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
  }
}
