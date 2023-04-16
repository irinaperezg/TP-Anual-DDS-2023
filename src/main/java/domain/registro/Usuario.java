package domain.registro;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Usuario {
    private String nombre;
    private String contrasenia;
    private String email;


    public Usuario(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }
}
