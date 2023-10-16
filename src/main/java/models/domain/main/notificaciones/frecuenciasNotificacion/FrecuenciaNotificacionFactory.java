package models.domain.main.notificaciones.frecuenciasNotificacion;

public class FrecuenciaNotificacionFactory {

    public static FrecuenciaNotificacion obtenerPorNombre(String nombre) {
        switch (nombre.toUpperCase()) {
            case "CUANDO_SUCEDA":
                return new NotificacionCuandoSucedeIncidente();
            case "SIN_APUROS":
                return new NotificacionSinApuros();
            default:
                throw new IllegalArgumentException("Nombre de frecuencia no reconocido: " + nombre);
        }
    }
}
