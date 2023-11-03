package server.utils;

import models.domain.apis.georef.ServicioGeoref;
import models.domain.apis.georef.adapters.ServicioGeorefRetrofitAdapter;
import models.domain.main.localizacion.Localidad;
import models.domain.main.localizacion.Localizacion;
import models.domain.main.localizacion.Provincia;
import models.domain.main.localizacion.TipoLocalizacion;
import models.domain.usuarios.roles.TipoRol;
import models.indice.Menu;
import models.repositorios.LocalizacionRepository;
import models.repositorios.MenuRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inicializador {

  public LocalizacionRepository localizacionRepository;
  public ServicioGeoref servicioGeoref;
  public MenuRepository menuRepository;

  public Inicializador()
  {

    this.localizacionRepository = new LocalizacionRepository();
    this.servicioGeoref = new ServicioGeoref();
    this.servicioGeoref.setAdapter(new ServicioGeorefRetrofitAdapter());
    this.menuRepository = new MenuRepository();
  }

  public void inicializar() throws IOException {
    //this.localizacionRepository.cargarTodo();
    List<Provincia> provincias = this.inicializarProvincias();
    List<Localizacion> localizaciones = this.inicializarLocalizaciones(provincias);
    this.inicializarLocalidades(localizaciones);
    inicializarMenus();
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
        List<Localizacion> municipiosNuevos = (this.servicioGeoref.listadoDeMunicipiosDeProvincia(provincia.getId().intValue())).getMunicipios();
        municipiosNuevos.forEach(municipioNuevo -> {
          municipioNuevo.setTipoLocalizacion(TipoLocalizacion.Municipio);
          municipioNuevo.setProvincia(provincia);
        });
        List<Localizacion> departamentosNuevos = this.servicioGeoref.listadoDeDepartamentosDeProvincia(provincia.getId().intValue()).getDepartamentos();

        departamentosNuevos.forEach(departamentoNuevo -> {
          departamentoNuevo.setTipoLocalizacion(TipoLocalizacion.Departamento);
          departamentoNuevo.setProvincia(provincia);
        });

        localizaciones.addAll(municipiosNuevos);
        localizaciones.addAll(departamentosNuevos);
      }
      localizacionRepository.persistirLocalizaciones(localizaciones);
    }
    return localizaciones;
  }
  public List<Localidad> inicializarLocalidades(List<Localizacion> localizaciones) throws IOException {
    List <Localidad> localidades = this.localizacionRepository.todasLasLocalidades();
    if (localidades.isEmpty()) {
      for (Localizacion localizacion : localizaciones) {
        List<Localidad> localidadesNuevas =this.servicioGeoref.listadoDeLocalidades(localizacion.getId().intValue(),
                localizacion.getTipoLocalizacion()).getLocalidades();

        for (Localidad localidadNueva : localidadesNuevas) {
          localidadNueva.setLocalizacion(localizacion);
        }

        localidades.addAll(localidadesNuevas);
      }

      localizacionRepository.persistirLocalidades(localidades);
    }
    return localidades;
  }

  public void inicializarMenus() {
    List<Menu> menus = this.menuRepository.all();
    if (menus.isEmpty()) {

        menus.add(new Menu("/inicio", "Inicio", TipoRol.CONSUMIDOR));
        menus.add(new Menu("/inicio", "Inicio", TipoRol.ADMINISTRADOR));
        menus.add(new Menu("/perfil", "Perfil", TipoRol.CONSUMIDOR));
        menus.add(new Menu("/perfil-Admin", "Perfil", TipoRol.ADMINISTRADOR));
        menus.add(new Menu("/incidentes", "Incidentes", TipoRol.CONSUMIDOR));
        menus.add(new Menu("/todos-incidentes", "Incidentes", TipoRol.ADMINISTRADOR));
        menus.add(new Menu("/comunidades", "Comunidades", TipoRol.CONSUMIDOR));
        menus.add(new Menu("/todas-comunidades", "Comunidades", TipoRol.ADMINISTRADOR));
        menus.add(new Menu("/rankings", "Rankings", TipoRol.CONSUMIDOR));
        menus.add(new Menu("/rankings", "Rankings", TipoRol.ADMINISTRADOR));
        menus.add(new Menu("/carga-masiva", "Carga Masiva", TipoRol.ADMINISTRADOR));

        menuRepository.persistir(menus);

    }

  }
}
