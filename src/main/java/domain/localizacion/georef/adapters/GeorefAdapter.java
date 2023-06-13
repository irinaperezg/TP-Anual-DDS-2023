package domain.localizacion.georef.adapters;

import domain.localizacion.georef.entities.ListadoDeDepartamentos;
import domain.localizacion.georef.entities.ListadoDeLocalidades;
import domain.localizacion.georef.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.ListadoDeProvincias;
import domain.localizacion.main.localizaciones.TipoLocalizacion;

import java.io.IOException;

public interface GeorefAdapter {

    ListadoDeProvincias listadoDeProvincias() throws IOException;
    ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException;
    ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException;

    ListadoDeLocalidades listadoDeLocalidades(int idLocalizacion, TipoLocalizacion tipoLocalizacion) throws IOException;
}
