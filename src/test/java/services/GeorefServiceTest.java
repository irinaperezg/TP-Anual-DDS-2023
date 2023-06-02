package services;

import domain.services.georef.ServicioGeoref;
import domain.services.georef.adapters.GeorefAdapter;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
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
        List<Provincia> provincias = this.provinciasMock();


        when(listadoDeProvinciasMock.getProvincias()).thenReturn(provincias);
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
        List<Municipio> municipiosMock = this.municipiosMock();
        ListadoDeMunicipios listadoDeMunicipiosMock = mock(ListadoDeMunicipios.class);

        when(listadoDeMunicipiosMock.getMunicipios()).thenReturn(municipiosMock);
        when(this.adapterMock.listadoDeMunicipiosDeProvincia(buenosAires.id)).thenReturn(listadoDeMunicipiosMock);

        Assertions.assertEquals(3, this.servicioGeoref.listadoDeMunicipiosDeProvincia(buenosAires.id).getMunicipios().size());
    }

    private List<Municipio> municipiosMock() {
        List<Municipio> municipios = new ArrayList<>();
        municipios.add(new Municipio(1, "Pilar"));
        municipios.add(new Municipio(2, "Bahia Blanca"));
        municipios.add(new Municipio(2, "Ezeiza"));
        return municipios;
    }

}
