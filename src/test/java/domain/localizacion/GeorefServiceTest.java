package domain.localizacion;

import domain.localizacion.georef.ServicioGeoref;
import domain.localizacion.georef.adapters.GeorefAdapter;
import domain.localizacion.georef.entities.ListadoDeMunicipios;
import domain.localizacion.georef.entities.ListadoDeProvincias;
import domain.localizacion.main.localizaciones.Municipio;
import domain.localizacion.main.Provincia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GeorefServiceTest {
    private ServicioGeoref servicioGeoref;
    private GeorefAdapter adapterMock;

    @BeforeEach
    public void init() {
        this.adapterMock = mock(GeorefAdapter.class);
        this.servicioGeoref = ServicioGeoref.instancia();
        this.servicioGeoref.setAdapter(this.adapterMock);
    }

    @Test
    public void ServicioGeorefProveeListadoDeProvinciasTest() throws IOException {
        ListadoDeProvincias listadoDeProvinciasMock = mock(ListadoDeProvincias.class);
        List<Provincia> provinciasMock = this.provinciasMock();


        when(listadoDeProvinciasMock.getProvincias()).thenReturn(provinciasMock);
        when(this.adapterMock.listadoDeProvincias()).thenReturn(listadoDeProvinciasMock);

        Assertions.assertEquals(2, this.servicioGeoref.listadoDeProvincias().getProvincias().size());
    }

    private List<Provincia> provinciasMock() {
        List<Provincia> provincias = new ArrayList<>();
        provincias.add(new Provincia(1, "Buenos Aires"));
        provincias.add(new Provincia(2, "Cordoba"));
        return provincias;
    }

    @Test
    public void ServicioGeorefProveeMunicipiosDeBuenosAiresTest() throws IOException {
        Provincia buenosAires = new Provincia(1, "Buenos Aires");

        ListadoDeMunicipios listadoDeMunicipiosMock = mock(ListadoDeMunicipios.class);
        List<Municipio> municipiosMock = this.municipiosMock();


        when(listadoDeMunicipiosMock.getMunicipios()).thenReturn(municipiosMock);
        when(this.adapterMock.listadoDeMunicipiosDeProvincia(buenosAires.getId())).thenReturn(listadoDeMunicipiosMock);

        Assertions.assertEquals(3, this.servicioGeoref.listadoDeMunicipiosDeProvincia(buenosAires.getId()).getMunicipios().size());
    }

    private List<Municipio> municipiosMock() {
        List<Municipio> municipios = new ArrayList<>();
        municipios.add(new Municipio(2, "Pilar", new Provincia(1, "Buenos Aires")));
        municipios.add(new Municipio(3, "Bahia Blanca", new Provincia(1, "Buenos Aires")));
        municipios.add(new Municipio(4, "Ezeiza", new Provincia(1, "Buenos Aires")));
        return municipios;
    }

}
