package server.utils;

import models.domain.apis.georef.ServicioGeoref;
import models.domain.apis.georef.adapters.ServicioGeorefRetrofitAdapter;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
import models.domain.main.localizacion.Provincia;
import models.repositorios.LocalizacionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inicializador {

  public LocalizacionRepository localizacionRepository;
  public ServicioGeoref servicioGeoref;
  public Inicializador()
  {
    this.localizacionRepository = new LocalizacionRepository();
    this.servicioGeoref = new ServicioGeoref();
    this.servicioGeoref.setAdapter(new ServicioGeorefRetrofitAdapter());
  }

  public void inicializar() throws IOException {
    List<Provincia> provincias = this.inicializarProvincias();
    List<Localizacion> localizaciones = this.inicializarLocalizaciones(provincias);
    List<Localidad> localidades = this.inicializarLocalidades(localizaciones);
  }

  public List<Provincia> inicializarProvincias() {
    List<Provincia> provincias = this.localizacionRepository.todasLasProvincias();
    if (provincias.isEmpty()) {
      try {
        provincias = this.servicioGeoref.listadoDeProvincias().getProvincias();
        localizacionRepository.persistirProvincias(provincias);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return provincias;
  }

  public List<Localizacion> inicializarLocalizaciones(List<Provincia> provincias) throws IOException {
    List <Localizacion> localizaciones = this.localizacionRepository.todasLasLocalizaciones();
    if (localizaciones.isEmpty()) {
      for (Provincia provincia : provincias) {
        localizaciones.addAll(this.servicioGeoref.listadoDeMunicipiosDeProvincia(provincia.getId().intValue()).getMunicipios());
        localizaciones.addAll(this.servicioGeoref.listadoDeDepartamentosDeProvincia(provincia.getId().intValue()).getDepartamentos());
      }
      localizacionRepository.persistirLocalizaciones(localizaciones);
    }
    return localizaciones;
  }
  public List<Localidad> inicializarLocalidades(List<Localizacion> localizaciones) throws IOException {
    List <Localidad> localidades = this.localizacionRepository.todasLasLocalidades();
    if (localidades.isEmpty()) {
      for (Localizacion localizacion : localizaciones) {
        localidades.addAll(this.servicioGeoref.listadoDeLocalidades(localizacion.getId().intValue(),
            localizacion.getTipoLocalizacion()).getLocalidades());
      }
      localizacionRepository.persistirLocalidades(localidades);
    }
    return localidades;
  }
  }
