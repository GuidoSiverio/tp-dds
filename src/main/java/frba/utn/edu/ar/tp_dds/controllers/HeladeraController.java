package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.services.HeladeraService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class HeladeraController {

  private final HeladeraService heladeraService;

  public HeladeraController(HeladeraService heladeraService) {
    this.heladeraService = heladeraService;
  }

  @PostMapping(path = "/heladeras", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> createHeladera(@RequestBody HeladeraDTO heladeraDTO) {
    heladeraService.save(heladeraDTO);
    return new ResponseEntity<>("Heladera creada correctamente!", HttpStatus.OK);
  }

  @PutMapping(path = "/heladeras/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> editHeladera(@PathVariable Long id, @RequestBody HeladeraDTO heladeraDTO) {
    heladeraService.update(id, heladeraDTO);
    return new ResponseEntity<>("Heladera editada correctamente!", HttpStatus.OK);
  }

  @DeleteMapping(path = "/heladeras/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> deleteHeladera(@PathVariable Long id) {
    heladeraService.delete(id);
    return new ResponseEntity<>("Heladera eliminada correctamente!", HttpStatus.OK);
  }

  @GetMapping(path = "/heladeras", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Heladera>> getHeladeras() {
    List<Heladera> heladeras = heladeraService.findAll();
    return new ResponseEntity<>(heladeras, HttpStatus.OK);
  }

}
