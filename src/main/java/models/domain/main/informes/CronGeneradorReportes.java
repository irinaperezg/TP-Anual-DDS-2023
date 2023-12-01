package models.domain.main.informes;

import models.config.Config;
import models.domain.main.EntidadPrestadora;
import models.domain.main.exportar.ApachePDFBox;
import models.domain.main.exportar.EstrategiaExportacionPDF;
import models.domain.main.informes.rankings.Reporte;
import models.repositorios.EntidadPrestadoraRepository;
import models.repositorios.ReportesRepository;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CronGeneradorReportes extends TimerTask {

    private EntidadPrestadoraRepository repositorioDeEntidadPrestadora = new EntidadPrestadoraRepository();
    private ReportesRepository repositorioDeReportes =  new ReportesRepository();

    public CronGeneradorReportes() {
        Timer t = new Timer();
        t.scheduleAtFixedRate(this, 0, Config.TIEMPO_REPORTES);
    }

    @Override
    public void run() {
        GeneradorDeReportes generadorDeReportes = new GeneradorDeReportes(new EstrategiaExportacionPDF(new ApachePDFBox()));
        List<EntidadPrestadora> entidadesPreestadoras = this.repositorioDeEntidadPrestadora.todos();
        for (EntidadPrestadora entidadPrestadora: entidadesPreestadoras) {
            try {
                List<Reporte> reportes = generadorDeReportes.generadorSemanalDeReportes(entidadPrestadora);
                for (Reporte reporte: reportes) {
                    this.repositorioDeReportes.guardar(reporte);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
