package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.dto.VisitaDTO;
import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.services.TecnicoService;
import frba.utn.edu.ar.tp_dds.services.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping(path = "/tecnicos/visitas", consumes = "multipart/form-data" )
    public ResponseEntity<String> registrarVisita(@RequestParam("heladeraId") Long heladeraId,
                                                  @RequestParam("tecnicoId") Long tecnicoId,
                                                  @RequestParam("incidenteId") Long incidenteId,
                                                  @RequestParam("comentario") String comentario,
                                                  @RequestParam("solucionado") boolean solucionado,
                                                  @RequestParam("imagen") MultipartFile imagen) {

        try {
            String folder = "uploads/";
            byte[] bytes = imagen.getBytes();
            Path path = Paths.get(folder + imagen.getOriginalFilename());
            Files.write(path, bytes);

            VisitaDTO visitaDTO = new VisitaDTO(comentario, solucionado, folder + imagen.getOriginalFilename(), tecnicoId, heladeraId, incidenteId);
            visitaService.registrarVisita(visitaDTO);
            return new ResponseEntity<>("Visita registrada correctamente!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al guardar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
