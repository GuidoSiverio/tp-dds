package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteFallasPorHeladera;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorColaborador;
import frba.utn.edu.ar.tp_dds.entities.reporte.ReporteViandasPorHeladera;
import frba.utn.edu.ar.tp_dds.services.ReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/fallas-por-heladera")
    public ResponseEntity<List<ReporteFallasPorHeladera>> obtenerFallasPorHeladera() {
        return new ResponseEntity<>(reporteService.obtenerFallasPorHeladera(), HttpStatus.OK);
    }

    @GetMapping("/viandas-por-heladera")
    public ResponseEntity<List<ReporteViandasPorHeladera>> obtenerViandasPorHeladera() {
        return new ResponseEntity<>(reporteService.obtenerViandasPorHeladera(), HttpStatus.OK);
    }

    @GetMapping("/viandas-por-colaborador")
    public ResponseEntity<List<ReporteViandasPorColaborador>> obtenerViandasPorColaborador() {
        return new ResponseEntity<>(reporteService.obtenerViandasPorColaborador(), HttpStatus.OK);
    }

}
