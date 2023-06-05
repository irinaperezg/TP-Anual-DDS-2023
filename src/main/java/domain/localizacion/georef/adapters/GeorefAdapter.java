package domain.localizacion.georef.adapters;

import domain.localizacion.georef.entities.entities.ListadoDeDepartamentos;
import domain.localizacion.georef.entities.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.entities.ListadoDeProvincias;

import java.io.IOException;

public interface GeorefAdapter {

    public ListadoDeProvincias listadoDeProvincias() throws IOException;
    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException;
    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException;
}
