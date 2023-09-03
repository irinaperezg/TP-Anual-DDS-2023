package org.example;

import org.example.condicionesDeFusion.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorDeFusionDeComunidades {
    private ArrayList<Fusion> fusiones = new ArrayList<>();
    private ArrayList<CondicionDeFusion> condicionesBasicasDeFusiones;

    public GestorDeFusionDeComunidades() {
        this.condicionesBasicasDeFusiones.add(new EstablecimientosObservados());
        this.condicionesBasicasDeFusiones.add(new ServiciosObservados());
        this.condicionesBasicasDeFusiones.add(new TienenMismoGradoDeConfianza());
        this.condicionesBasicasDeFusiones.add(new UsuariosEnComun());
    }

    public List<Fusion> sugerirFusion(List<Comunidad> comunidades) {
        List<Fusion> propuestas = new ArrayList<>();

        for(Comunidad comunidad1 : comunidades) {
            for (Comunidad comunidad2 : comunidades) {
                if (!Objects.equals(comunidad1.id, comunidad2.id) && this.cumpleCondicionesDeFusion(comunidad1, comunidad2)) {
                    propuestas.add(crearPropuestaDeFusion(comunidad1, comunidad2));
                    comunidades.remove(comunidad1);
                    comunidades.remove(comunidad2);
                }
            }
        }

        fusiones.addAll(propuestas);
        return propuestas;
    }

    public Fusion crearPropuestaDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        Fusion nuevaPropuesta = new Fusion();
        nuevaPropuesta.comunidad1 = comunidad1;
        nuevaPropuesta.comunidad2 = comunidad2;
        nuevaPropuesta.fechaCreada = LocalDateTime.now();
        nuevaPropuesta.estado = EstadoFusion.PROPUESTA;
        return nuevaPropuesta;
    }

    public Boolean cumpleCondicionesDeFusion(Comunidad comunidad1, Comunidad comunidad2) {
        return this.cumpleTemporalidadExistentes(comunidad1, comunidad2)
                && condicionesBasicasDeFusiones.stream().allMatch(condicionDeFusion ->
                    condicionDeFusion.cumpleCondicionDeFusion(comunidad1, comunidad2));
    }

    private Boolean cumpleTemporalidadExistentes (Comunidad comunidad1, Comunidad comunidad2) { //esto puede ser una condicionDeFusion mas
        Integer cantidadMesesDiferencia = Integer.parseInt(Config.obtenerInstancia().obtenerDelConfig("mesesDeDiferenciaEntrePropuestas"));
        List<Fusion> fusionesExistentes;
        fusionesExistentes = fusiones.stream().filter(fusion -> fusion.mismasComunidades(comunidad1, comunidad2)
                && LocalDateTime.now().getMonthValue() > fusion.fechaCreada.getMonthValue() + cantidadMesesDiferencia).toList();
        return fusionesExistentes.size() > 0;
    }

    public Fusion fusionarComunidades(Fusion fusion) {
        this.crearComunidad(fusion);
        fusion.estado = EstadoFusion.REALIZADA;
        return fusion;
    }

    public void crearComunidad(Fusion fusion) {
        Comunidad comunidadFinal = new Comunidad();
        comunidadFinal.idEstablecimientoObservados = new ArrayList<>();
        comunidadFinal.idEstablecimientoObservados.addAll(fusion.comunidad1.idEstablecimientoObservados);
        comunidadFinal.idEstablecimientoObservados.addAll(fusion.comunidad2.idEstablecimientoObservados);
        comunidadFinal.idServiciosObservados = new ArrayList<>();
        comunidadFinal.idServiciosObservados.addAll(fusion.comunidad1.idServiciosObservados);
        comunidadFinal.idServiciosObservados.addAll(fusion.comunidad2.idServiciosObservados);
        comunidadFinal.gradoDeConfianza = fusion.comunidad1.gradoDeConfianza;
        fusion.comunidadFinal = comunidadFinal;
    }
}