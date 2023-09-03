package org.example.condicionesDeFusion;

import org.example.Calculador;
import org.example.Comunidad;
import org.example.Config;

public class UsuariosEnComun implements CondicionDeFusion {

    float porcentajeRequerido = Float.parseFloat(Config.obtenerInstancia().obtenerDelConfig("porcentajeEnComunUsuarios"));

    public Boolean cumpleCondicionDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        float porcentajeEnComun = Calculador.obtenerInstancia().calcularPorcentajeEnComun(comunidad1.idMiembros, comunidad2.idMiembros);

        return porcentajeEnComun > porcentajeRequerido;
    }
}
