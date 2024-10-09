package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.services.HeladeraService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HeladeraController {

  private final HeladeraService heladeraService;

  public HeladeraController(HeladeraService heladeraService) {
    this.heladeraService = heladeraService;
  }

  @PostMapping(path = "/heladeras", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerHeladera(@RequestBody Heladera heladera) {
    heladeraService.save(heladera);
    return new ResponseEntity<>("Heladera registered successfully!", HttpStatus.OK);
  }

  @GetMapping(path = "/heladeras", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Heladera>> getHeladeras() {
    List<Heladera> heladeras = heladeraService.findAll();
    return new ResponseEntity<>(heladeras, HttpStatus.OK);
  }

}
