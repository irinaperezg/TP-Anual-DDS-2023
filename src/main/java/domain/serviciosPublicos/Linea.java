package domain.serviciosPublicos;

import domain.estaciones.Estacion;

import java.util.List;

public class Linea {
    private String nombre;
    private Estacion estacionOrigen;
    private Estacion estacionDestino;
    private List<Estacion> recorrido;
}