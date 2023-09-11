package domain.localizacion.main;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Localizacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="nombre")
  private String nombre;

  @ManyToOne
  @JoinColumn(name="provincia_id", referencedColumnName = "id")
  private Provincia provincia;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_localizacion")
  private TipoLocalizacion tipoLocalizacion;

  public Localizacion(Long id, String nombre, Provincia provincia, TipoLocalizacion tipo) {
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
    this.tipoLocalizacion = tipo;
  }

  public Localizacion() {
    this.id = null;
    this.nombre = null;
    this.provincia = null;
    this.tipoLocalizacion = null;
  }

  public boolean esIgualA(Localizacion localizacion) {
    return this.id.equals(localizacion.getId()) && this.nombre.equals(localizacion.getNombre()) && this.provincia.esIgualA(localizacion.getProvincia());
  }
}

