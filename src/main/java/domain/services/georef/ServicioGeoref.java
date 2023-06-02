package domain.services.georef;
import domain.services.georef.adapters.GeorefAdapter;
import domain.services.georef.entities.ListadoDeDepartamentos;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
public class ServicioGeoref {
  private static ServicioGeoref instancia = null;
  private GeorefAdapter adapter;

  public void setAdapter(GeorefAdapter adapter) {
    this.adapter = adapter;
  }

  public static ServicioGeoref instancia(){
    if(instancia== null){
      instancia = new ServicioGeoref();
    }
    return instancia;
  }

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    return this.adapter.listadoDeProvincias();
  }

  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException {
    return this.adapter.listadoDeMunicipiosDeProvincia(idProvincia);
  }
  public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException {
    return this.adapter.listadoDeDepartamentosDeProvincia(idProvincia);
  }
}
