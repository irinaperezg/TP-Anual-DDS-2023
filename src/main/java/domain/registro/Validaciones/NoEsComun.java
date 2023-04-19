package domain.registro.Validaciones;

import domain.registro.Validacion;
import domain.excepciones.excepcionesContrasenias.ExcepcionComun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static config.Config.Archivo10kContrasenias;

public class NoEsComun implements Validacion {

    private final List<String> contraseniasComunes = new ArrayList<>();

    @Override
    public boolean validarContrasenia(String nombre, String contrasenia) {
        if (contraseniasComunes.contains(contrasenia)) {
            throw new ExcepcionComun("La contrasenia no debe ser una contrasenia comun");
        }
        return true;
    }

    public NoEsComun() {
        this.procesarArchivoDeContrasenasComunes();
    }

    public void procesarArchivoDeContrasenasComunes() {
        try {
            File file = new File(Archivo10kContrasenias);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contraseniasComunes.add(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo" + Archivo10kContrasenias);
            e.printStackTrace();
        }
    }
}