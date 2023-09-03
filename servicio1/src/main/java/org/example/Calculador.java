package org.example;

import java.util.List;

public class Calculador {
    private static Calculador instancia;

    private Calculador() {
    }

    public static Calculador obtenerInstancia() { // Singleton
        if (instancia == null){
            instancia = new Calculador();
        }
        return instancia;
    }

    public float calcularPorcentajeEnComun(List<Integer> lista1, List<Integer> lista2) {
        Integer cantidadDeElementosTotal = Math.min(lista1.size(), lista2.size());
        Integer elementosEnComun = 0;


        for(Integer elemento1 : lista1) {
            for(Integer elemento2 : lista2) {
                if(elemento1.equals(elemento2)) {
                    elementosEnComun++;
                }
            }
        }

        return (elementosEnComun / cantidadDeElementosTotal);
    }
}
