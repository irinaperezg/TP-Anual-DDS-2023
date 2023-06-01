package domain.services.georef.adapters;

import domain.services.georef.GeorefService;
import domain.services.georef.entities.ListadoDeDepartamentos;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Provincia;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public interface GeorefAdapter {

    public ListadoDeProvincias listadoDeProvincias() throws IOException;

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia);
    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(Provincia provincia);
}
