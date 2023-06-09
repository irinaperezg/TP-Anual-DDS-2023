package domain.localizacion.georef.adapters;

import domain.localizacion.georef.entities.ListadoDeDepartamentos;
import domain.localizacion.georef.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.ListadoDeProvincias;
import domain.localizacion.georef.GeorefService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import config.Config;

import java.io.IOException;

public class ServicioGeorefRetrofitAdapter implements GeorefAdapter{
    private static final String urlApi = new Config().obtenerDelConfig("apiGeoref");
    private final Retrofit retrofit;

    public ServicioGeorefRetrofitAdapter() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias("id,nombre");
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(idProvincia, "id,nombre");
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }
    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(idProvincia, "id,nombre");
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
        return responseListadoDeDepartamentos.body();
    }
}
