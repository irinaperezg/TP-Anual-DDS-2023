package server.middlewares;

import models.domain.apis.georef.ServicioGeoref;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
import models.domain.main.localizacion.Provincia;
import models.repositorios.LocalizacionRepository;

import java.io.IOException;
import java.util.List;

public class Inicializador {

  public LocalizacionRepository localizacionRepository;
  public ServicioGeoref servicioGeoref;
  public void inicializador(LocalizacionRepository localizacionRepository)
  {
    this.localizacionRepository = localizacionRepository;
    this.servicioGeoref = new ServicioGeoref();
  }

  public void inicializar()
  {

  }

  public void inicializarProvincias() {
    List<Provincia> provincias = this.localizacionRepository.todasLasProvincias();
    if (provincias.isEmpty()) {
      try {
        provincias = this.servicioGeoref.listadoDeProvincias().getProvincias();
        localizacionRepository.persistirProvincias(provincias);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void inicializarLocalidades() {
    //List<Localidad> localidades = this.localizacionRepository.todasLasLocalidades();
  }

  }
