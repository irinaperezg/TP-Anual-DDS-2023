package csv;

import domain.usuarios.Delegado;
import domain.main.EntidadPrestadora;
import domain.main.OrganismoDeControl;

public class ParserCSV {

  public EntidadPrestadora instanciarEntidadPrestadora(LineaCSV linea) {
    return new EntidadPrestadora(linea.getDenominacion(), new Delegado(linea.getNombreDelegado(),linea.getEmailDelegado()));
  }

  public OrganismoDeControl instanciarOrganismoDeControl(LineaCSV linea) {
    return new OrganismoDeControl(linea.getDenominacion(), new Delegado(linea.getNombreDelegado(),linea.getEmailDelegado()));
  }
}
