package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteFallasPorHeladera;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorColaborador;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorHeladera;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    private final HeladeraRepository heladeraRepository;
    private final ColaboradorRepository colaboradorRepository;

    public ReporteService(HeladeraRepository heladeraRepository, ColaboradorRepository colaboradorRepository) {
        this.heladeraRepository = heladeraRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<ReporteFallasPorHeladera> obtenerFallasPorHeladera() {

        List<ReporteFallasPorHeladera> reporteFallasPorHeladera = new ArrayList<>();
        for ( Object[] item : heladeraRepository.fallasPorHeladera()){
            reporteFallasPorHeladera.add(new ReporteFallasPorHeladera((String) item[0], (Long) item[1]));
        }
        return reporteFallasPorHeladera;
    }

    public List<ReporteViandasPorHeladera> obtenerViandasPorHeladera() {

        List<ReporteViandasPorHeladera> reporteViandasPorHeladeras = new ArrayList<>();
        for ( Object[] item : heladeraRepository.viandasPorHeladera()){
            reporteViandasPorHeladeras.add(new ReporteViandasPorHeladera((String) item[0], (Long) item[1]));
        }
        return reporteViandasPorHeladeras;
    }

    public List<ReporteViandasPorColaborador> obtenerViandasPorColaborador() {

        List<ReporteViandasPorColaborador> reporteViandasPorColaboradores = new ArrayList<>();
        for ( Object[] item : colaboradorRepository.viandasPorColaborador()){
            reporteViandasPorColaboradores.add(new ReporteViandasPorColaborador((String) item[0], (Long) item[1]));
        }
        return reporteViandasPorColaboradores;
    }
}
