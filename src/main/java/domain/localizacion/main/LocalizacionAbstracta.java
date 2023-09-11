package domain.localizacion.main;

import domain.localizacion.main.localizaciones.TipoLocalizacion;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_localizacion", discriminatorType = DiscriminatorType.STRING)
public abstract class LocalizacionAbstracta implements Localizacion{
    @Id
    protected Integer id;

    @Column
    protected String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    protected Provincia provincia;

    @Transient
    protected TipoLocalizacion tipoLocalizacion;
}
