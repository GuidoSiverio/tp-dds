package frba.utn.edu.ar.tp_dds.controllers;

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

  @PostMapping(path = "/colaboradores/persona-humana", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerPersonaHumana(@RequestBody PersonaHumana personaHumana) {
    colaboradorService.save(personaHumana);
    return new ResponseEntity<>("PersonaHumana registered successfully!", HttpStatus.OK);
  }

  @PostMapping(path = "/colaboradores/persona-juridica", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> registerPersonaJuridica(@RequestBody PersonaJuridica personaJuridica) {
    colaboradorService.save(personaJuridica);
    return new ResponseEntity<>("PersonaJuridica registered successfully!", HttpStatus.OK);
  }

  //quiero el modificar colaborador y el eliminar colaborador
  //modificar colaborador

  @PutMapping(path = "/colaboradores/persona-humana/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> editarPersonaHumana(@PathVariable Long id, @RequestBody PersonaHumana personaHumana) {
    colaboradorService.update(id, personaHumana);
    return new ResponseEntity<>("Persona humana editada correctamente!", HttpStatus.OK);
  }

  @PutMapping(path = "/colaboradores/persona-juridica/{id}", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> editarPersonaJuridica(@PathVariable Long id, @RequestBody PersonaJuridica personaJuridica) {
    colaboradorService.update(id, personaJuridica);
    return new ResponseEntity<>("Persona juridica editada correctamente!", HttpStatus.OK);
  }
  //eliminar colaborador

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
