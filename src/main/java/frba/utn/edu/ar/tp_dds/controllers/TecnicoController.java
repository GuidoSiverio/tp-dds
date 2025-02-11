package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.dto.VisitaDTO;
import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.services.TecnicoService;
import frba.utn.edu.ar.tp_dds.services.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TecnicoController {

    private final TecnicoService tecnicoService;
    private final VisitaService visitaService;

    public TecnicoController(TecnicoService tecnicoService, VisitaService visitaService) {
        this.tecnicoService = tecnicoService;
        this.visitaService = visitaService;
    }

    @PostMapping(path = "/tecnicos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createTecnico(@RequestBody TecnicoDTO tecnicoDTO) {
        tecnicoService.save(tecnicoDTO);
        return new ResponseEntity<>("Tecnico creado correctamente!", HttpStatus.OK);
    }

    @PutMapping(path = "/tecnicos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> editTecnico(@PathVariable Long id, @RequestBody TecnicoDTO tecnicoDTO) {
        tecnicoService.update(id, tecnicoDTO);
        return new ResponseEntity<>("Tecnico editado correctamente!", HttpStatus.OK);
    }

    @DeleteMapping(path = "/tecnicos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteTecnico(@PathVariable Long id) {
        tecnicoService.delete(id);
        return new ResponseEntity<>("Tecnico eliminado correctamente!", HttpStatus.OK);
    }

    @GetMapping(path = "/tecnicos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Tecnico>> getTecnicos() {
        List<Tecnico> tecnicos = tecnicoService.findAll();
        return new ResponseEntity<>(tecnicos, HttpStatus.OK);
    }

    @PostMapping(path = "/tecnicos/visitas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registrarVisita(@RequestBody VisitaDTO visitaDTO) {
        visitaService.registrarVisita(visitaDTO);
        return new ResponseEntity<>("Visita registrada correctamente!", HttpStatus.OK);
    }
}
