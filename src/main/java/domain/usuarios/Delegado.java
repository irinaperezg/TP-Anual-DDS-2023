package domain.usuarios;

public class Delegado {
    private Usuario usuario;

    public Delegado(String nombre, String email) {
        this.usuario = buscarUsuario(nombre, email);
    }

    private Usuario buscarUsuario(String nombre, String email) {
        //TODO cuando tengamos base de datos buscar al usuario con una query
        return new Usuario(nombre, "hola", email);
    }
}
