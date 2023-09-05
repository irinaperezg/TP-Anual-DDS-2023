package domain.usuarios;

import domain.Persistente;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name="usuario")
public class Usuario extends Persistente {
  @Column(name="nombre")
  private String nombre;
  @Column(name="contrasenia")
  private String contraseniaEncriptada;

  public Usuario(String nombre, String contraseniaEncriptada) {
    this.nombre = nombre;
    this.contraseniaEncriptada = contraseniaEncriptada;
  }
}
