package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.ColaboradorDTO;
import frba.utn.edu.ar.tp_dds.entities.User;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import frba.utn.edu.ar.tp_dds.services.ColaboradorService;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ColaboradorController {

  private final ColaboradorService colaboradorService;

  public ColaboradorController(ColaboradorService colaboradorService) {
    this.colaboradorService = colaboradorService;
  }

  @PostMapping(path = "/colaboradores", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
    colaboradorService.save(colaboradorDTO);
    return new ResponseEntity<>("Colaborador creado correctamente!", HttpStatus.OK);
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
}
