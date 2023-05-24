package domain.usuarios;

import domain.localizacion.Localizacion;
import java.util.List;
import lombok.Getter;


@Getter
public class Usuario {
  private String nombre;
  private String contraseniaEncriptada;
  private String email;
  private List<Interes> intereses;
  private Localizacion localizacion;


  public Usuario(String nombre, String contraseniaEncriptada) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
  }
}
