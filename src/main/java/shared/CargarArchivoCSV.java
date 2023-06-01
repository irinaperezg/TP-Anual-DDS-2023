package shared;

import domain.entidades.Entidad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargarArchivoCSV {
    public static void leerArchivoCSV(String[] args) {
        String archivoCSV = "ruta/del/archivo.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Procesa cada línea del archivo CSV aquí
                for (String dato : datos) {
                    System.out.print(dato + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}