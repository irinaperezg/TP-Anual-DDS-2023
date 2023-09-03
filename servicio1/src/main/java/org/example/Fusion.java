package org.example;

import java.time.LocalDateTime;
import java.util.Date;

public class Fusion {
    EstadoFusion estado;
    Comunidad comunidad1;
    Comunidad comunidad2;
    Comunidad comunidadFinal;
    LocalDateTime fechaCreada;

    public Boolean mismasComunidades (Comunidad a, Comunidad b) {
        return (comunidad1.equals(a) && comunidad2.equals(b))||(comunidad1.equals(b) && comunidad2.equals(a));
    }
}