package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import frba.utn.edu.ar.tp_dds.services.ColaboradorService;
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

  @GetMapping(path = "/colaboradores", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Colaborador>> getColaboradores() {
    List<Colaborador> colaboradores = colaboradorService.findAll();
    return new ResponseEntity<>(colaboradores, HttpStatus.OK);
  }
}
