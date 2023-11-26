package models.domain.main.exportar;

import models.domain.main.informes.PosicionRanking;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.*;

import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Config;

public class Informe implements Exportable {

    public Map<String, List<String>> datos = new HashMap<>();

    public List<PosicionRanking> posiciones = new ArrayList<>();

    public String ruta;

    public Informe(List<PosicionRanking> posiciones) {
        this.posiciones = posiciones;
    }

    @Override
    public Map<String, List<String>> getDatos() {
        return datos;
    }
    
    public Map<String, List<String>> procesoDatosEntrantes() {
      List<String> topicos = Arrays.asList("Nombre", "Tipo","Puntaje");
        int i = 0;
        datos.put(String.valueOf(i), topicos);
        for(PosicionRanking entrada : posiciones){
            String nombre = entrada.getEntidad().getDenominacion();
            String tipo = entrada.getEntidad().getTipo().getTipoEntidad();
            String puntaje = String.valueOf(entrada.getPuntaje());
            datos.put(String.valueOf(i), Arrays.asList(nombre, tipo,puntaje));
            i++;
        }
        return this.datos;
    }
}
