package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.services.ViandaService;
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
public class ViandaController {

  private final ViandaService viandaService;

  public ViandaController(ViandaService viandaService) {
    this.viandaService = viandaService;
  }

  @PostMapping(path = "/viandas", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerVianda(@RequestBody Vianda vianda) {
    viandaService.save(vianda);
    return new ResponseEntity<>("Vianda registered successfully!", HttpStatus.OK);
  }

  @GetMapping(path = "/viandas", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Vianda>> getViandas() {
    List<Vianda> viandas = viandaService.findAll();
    return new ResponseEntity<>(viandas, HttpStatus.OK);
  }

}
