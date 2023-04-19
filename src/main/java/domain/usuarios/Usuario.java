package domain.usuarios;

import lombok.Getter;

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
