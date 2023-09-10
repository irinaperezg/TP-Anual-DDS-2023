package domain.localizacion.main;

import domain.localizacion.main.localizaciones.TipoLocalizacion;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo_localizacion", discriminatorType = DiscriminatorType.STRING)
public abstract class LocalizacionAbstracta {
    @Id
    protected Integer id;

    @Column
    protected String nombre;

    @Embedded
    protected Provincia provincia;

    @Transient
    protected TipoLocalizacion tipoLocalizacion;
}
