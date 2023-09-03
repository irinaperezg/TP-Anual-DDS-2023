package org.example.condicionesDeFusion;

import org.example.Comunidad;
import org.example.Calculador;
import org.example.Config;

public class EstablecimientosObservados implements CondicionDeFusion {

    float porcentajeRequerido = Float.parseFloat(Config.obtenerInstancia().obtenerDelConfig("porcentajeEnComunEstablecimientos"));


    public Boolean cumpleCondicionDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        float porcentajeEnComun = Calculador.obtenerInstancia().calcularPorcentajeEnComun(comunidad1.idEstablecimientoObservados, comunidad2.idEstablecimientoObservados);

        return porcentajeEnComun > porcentajeRequerido;
    }
}
