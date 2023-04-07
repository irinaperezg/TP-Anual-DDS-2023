package domain.usuarios;

import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private List<Comunidad> comunidades;
    //TODO


    public Persona(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }
}
