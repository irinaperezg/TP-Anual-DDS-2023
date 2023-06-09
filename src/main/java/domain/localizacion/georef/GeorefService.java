package domain.localizacion.georef;

import domain.localizacion.georef.entities.ListadoDeDepartamentos;
import domain.localizacion.georef.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
  @GET("provincias")
  Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

  @GET("municipios")
  Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

  @GET("departamentos")
  Call<ListadoDeDepartamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos);
}