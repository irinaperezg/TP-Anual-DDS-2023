package domain.localizacion.georef;
import domain.localizacion.georef.adapters.GeorefAdapter;
import domain.localizacion.georef.entities.entities.ListadoDeDepartamentos;
import domain.localizacion.georef.entities.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.entities.ListadoDeProvincias;

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
