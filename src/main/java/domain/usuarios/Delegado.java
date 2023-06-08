package domain.usuarios;

import lombok.Getter;

@Getter
public class Delegado {
    private String nombre;
    private String email;

    public Delegado(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
