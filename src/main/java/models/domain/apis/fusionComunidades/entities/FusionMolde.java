package models.domain.apis.fusionComunidades.entities;

import java.time.LocalDateTime;

public class FusionMolde {
  private EstadoFusion estado;
  private ComunidadMolde comunidad1;
  private ComunidadMolde comunidad2;
  private LocalDateTime fechaCreada;

  public enum EstadoFusion {
    PROPUESTA,
    ACEPTADA,
    RECHAZADA
  }
}
