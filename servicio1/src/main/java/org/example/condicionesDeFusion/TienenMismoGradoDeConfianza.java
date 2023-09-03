package org.example.condicionesDeFusion;

import org.example.Comunidad;

import java.util.Objects;

public class TienenMismoGradoDeConfianza implements CondicionDeFusion {
    public Boolean cumpleCondicionDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.gradoDeConfianza == comunidad2.gradoDeConfianza;
    }
}
