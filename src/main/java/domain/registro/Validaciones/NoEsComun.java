package domain.registro.Validaciones;

import domain.registro.Validacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NoEsComun implements Validacion {

    private final List<String> contraseniasComunes = new ArrayList<>();

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) {
        if (esComun(contrasenia)) {
            //TODO exception
        }
        return true;
    }

    public NoEsComun() throws Exception {
        this.procesarArchivoDeContrasenasComunes();
    }

    private boolean esComun(String contrasenia) {
        if(contraseniasComunes.contains(contrasenia)){
            //throw new Exception("La contrasena no debe ser una contrasena comun");
        }
        return true;
    }

    public void procesarArchivoDeContrasenasComunes() throws Exception {
        String pathArchivo = "/10k-worst-passwords.txt";

        try{
            InputStream inputStream = NoEsComun.class.getResourceAsStream(pathArchivo);
            assert inputStream != null;
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            String contrasena;
            while((contrasena = reader.readLine()) != null){
                contraseniasComunes.add(contrasena);
            }
            reader.close();
        }catch (IOException e) {
            throw new Exception("Ocurrio un error al leer el archivo de contrasenas");
        }
    }
}