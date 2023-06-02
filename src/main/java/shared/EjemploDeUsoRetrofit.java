package shared;

import domain.services.georef.ServicioGeoref;
import domain.services.georef.adapters.ServicioGeorefRetrofitAdapter;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class EjemploDeUsoRetrofit {

    public static void main(String[] args) throws IOException {
        ServicioGeoref serviciosGeoref = ServicioGeoref.instancia();
        serviciosGeoref.setAdapter(new ServicioGeorefRetrofitAdapter());

        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        ListadoDeProvincias listadoDeProvinciasArgentinas = serviciosGeoref.listadoDeProvincias();

        listadoDeProvinciasArgentinas.provincias.sort(((p1, p2) -> p1.id >= p2.id ? 1 : -1));

        for (Provincia unaProvincia : listadoDeProvinciasArgentinas.provincias) {
            System.out.println((unaProvincia.id + ") " + unaProvincia.nombre));
        }

        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

        ListadoDeMunicipios municipiosDeLaProvincia = serviciosGeoref.listadoDeMunicipiosDeProvincia(idProvinciaElegida);
        System.out.println("los municipios de la provincia " + idProvinciaElegida + " son:");
        for (Municipio unMunicipio : municipiosDeLaProvincia.municipios) {
            System.out.println(unMunicipio.nombre);
        }
    }
}
