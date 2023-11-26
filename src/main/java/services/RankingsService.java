package services;

import models.domain.main.informes.PosicionRanking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingsService {

    public List<String> pasarRankingAString(List<PosicionRanking> ranking) {
        // Ordenar la lista por puntaje
        Collections.sort(ranking, new Comparator<PosicionRanking>() {
            @Override
            public int compare(PosicionRanking o1, PosicionRanking o2) {
                return Integer.compare(o1.getPuntaje(), o2.getPuntaje());
            }
        });

        // Crear la lista de denominaciones
        List<String> denominaciones = new ArrayList<>();
        for (PosicionRanking posicion : ranking) {
            denominaciones.add(posicion.getEntidad().getDenominacion());
        }

        return denominaciones;
    }
}
