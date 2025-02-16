package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.ColaboradorCsvDTO;
import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.services.ColaboradorService;
import frba.utn.edu.ar.tp_dds.services.SuscriptorService;
import frba.utn.edu.ar.tp_dds.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ColaboradorController {

  private final ColaboradorService colaboradorService;
  private final UserService userService;
  private final SuscriptorService suscriptorService;

  public ColaboradorController(ColaboradorService colaboradorService, UserService userService, SuscriptorService suscriptorService) {
    this.colaboradorService = colaboradorService;
      this.userService = userService;
      this.suscriptorService = suscriptorService;
  }

  @PostMapping(path = "/colaboradores", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Colaborador> registerColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
    Colaborador colaborador = colaboradorService.save(colaboradorDTO);
    userService.saveColab(colaborador, colaboradorDTO.getUsername(), colaboradorDTO.getPassword());
    return new ResponseEntity<>(colaborador, HttpStatus.OK);
  }

  @PutMapping(path = "/colaboradores/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> editarColaborador(@PathVariable Long id, @RequestBody ColaboradorDTO colaboradorDTO) {
    colaboradorService.update(id, colaboradorDTO);
    return new ResponseEntity<>("Colaborador editado correctamente!", HttpStatus.OK);
  }

  @DeleteMapping(path = "/colaboradores/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> eliminarColaborador(@PathVariable Long id) {
      colaboradorService.delete(id);
      return new ResponseEntity<>("Colaborador eliminado correctamente!", HttpStatus.OK);
  }

  @GetMapping(path = "/colaboradores", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Colaborador>> getColaboradores() {
    List<Colaborador> colaboradores = colaboradorService.findAll();
    return new ResponseEntity<>(colaboradores, HttpStatus.OK);
  }

  @PostMapping(path = "/colaboradores/masive", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> uploadColaboradores(@RequestBody List<ColaboradorCsvDTO> file) {
    try {
      colaboradorService.saveAllFromCsv(file);
      return new ResponseEntity<>("Colaboradores cargados correctamente!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error al cargar colaboradores: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/colaboradores/{id}/puntos", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Double> getPuntosColaborador(@PathVariable Long id) {
    Double puntos = colaboradorService.getPuntos(id);
    return new ResponseEntity<>(puntos, HttpStatus.OK);
  }

  @GetMapping(path = "/suscripciones/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Long>> getSuscripciones(@PathVariable Long id) {
        List<Long> heladerasIds = suscriptorService.getSuscripciones(id);
        return new ResponseEntity<>(heladerasIds, HttpStatus.OK);
    }
}
