package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import frba.utn.edu.ar.tp_dds.dto.RecomendacionDTO;
import frba.utn.edu.ar.tp_dds.dto.RecomendacionRequest;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.services.HeladeraService;

import java.io.IOException;
import java.util.List;

import frba.utn.edu.ar.tp_dds.services.RecomendacionService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class HeladeraController {

  private final HeladeraService heladeraService;
  private final RecomendacionService recomendacionService;

  public HeladeraController(HeladeraService heladeraService, RecomendacionService recomendacionService) {
    this.heladeraService = heladeraService;
    this.recomendacionService = recomendacionService;
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

  @PostMapping(path = "/heladeras/recomendaciones", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<RecomendacionDTO>> obtenerRecomendaciones(@RequestBody RecomendacionRequest request) {
    try {
      List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerRecomendaciones(
              request.getLatitud(),
              request.getLongitud(),
              request.getRadio()
      );
      return new ResponseEntity<>(recomendaciones, HttpStatus.OK);
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "/heladeras/{heladeraId}/suscribirse/{colaboradorId}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> suscribirse(@PathVariable Long heladeraId, @PathVariable Long colaboradorId) {
      heladeraService.suscribirse(heladeraId, colaboradorId);
      return new ResponseEntity<>("Suscripción realizada correctamente!", HttpStatus.OK);
  }

  @PostMapping(path = "/heladeras/{heladeraId}/desuscribirse/{colaboradorId}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> desuscribirse(@PathVariable Long heladeraId, @PathVariable Long colaboradorId) {
    heladeraService.desuscribirse(heladeraId, colaboradorId);
    return new ResponseEntity<>("Suscripción realizada correctamente!", HttpStatus.OK);
  }

  @PostMapping(path = "/heladeras/{id}/incidentes", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> reportarIncidente(@PathVariable Long id, @RequestBody IncidenteDTO incidenteDTO) {
    heladeraService.reportarFalla(id, incidenteDTO);
    return new ResponseEntity<>("Incidente reportado exitosamente!", HttpStatus.OK);
  }

  @GetMapping(path = "/heladeras/{id}/incidentes", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Incidente>> getIncidentes(@PathVariable Long id) {
        List<Incidente> incidentes = heladeraService.getIncidentes(id);
        return new ResponseEntity<>(incidentes, HttpStatus.OK);
    }

}
