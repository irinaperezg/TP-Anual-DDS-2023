package org.example.condicionesDeFusion;

import org.example.Calculador;
import org.example.Comunidad;
import org.example.Config;

public class ServiciosObservados implements CondicionDeFusion {

    float porcentajeRequerido = Float.parseFloat(Config.obtenerInstancia().obtenerDelConfig("porcentajeEnComunServicios"));

    public Boolean cumpleCondicionDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        float porcentajeEnComun = Calculador.obtenerInstancia().calcularPorcentajeEnComun(comunidad1.idServiciosObservados, comunidad2.idServiciosObservados);

        return porcentajeEnComun > porcentajeRequerido;
    }
}
