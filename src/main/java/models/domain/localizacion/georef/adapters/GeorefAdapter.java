package models.domain.localizacion.georef.adapters;

import models.domain.localizacion.georef.entities.ListadoDeDepartamentos;
import models.domain.localizacion.georef.entities.ListadoDeLocalidades;
import models.domain.localizacion.georef.entities.ListadoDeMunicipios;
import models.domain.localizacion.georef.entities.ListadoDeProvincias;
import models.domain.localizacion.main.TipoLocalizacion;

import java.io.IOException;

public interface GeorefAdapter {

    ListadoDeProvincias listadoDeProvincias() throws IOException;
    ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException;
    ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException;

    ListadoDeLocalidades listadoDeLocalidades(int idLocalizacion, TipoLocalizacion tipoLocalizacion) throws IOException;
}
