package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.usuarios.Delegado;

import java.util.List;
public class OrganismoDeControl {
    private String denominacion;
    private Delegado delegado;
    private List<EntidadPrestadora> entidadPrestadorasControladas;
}
