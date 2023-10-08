package models.domain.localizacion.georef;
import models.domain.localizacion.georef.adapters.GeorefAdapter;
import models.domain.localizacion.georef.entities.ListadoDeDepartamentos;
import models.domain.localizacion.georef.entities.ListadoDeLocalidades;
import models.domain.localizacion.georef.entities.ListadoDeMunicipios;
import models.domain.localizacion.georef.entities.ListadoDeProvincias;
import models.domain.localizacion.main.TipoLocalizacion;

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

  public ListadoDeLocalidades listadoDeLocalidades(int idLocalizacion, TipoLocalizacion tipoLocalizacion) throws IOException {
    return this.adapter.listadoDeLocalidades(idLocalizacion, tipoLocalizacion);
  }
}