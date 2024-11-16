package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.services.PersonaVulnerableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PersonaVulnerableController {

    private final PersonaVulnerableService personaVulnerableService;

    public PersonaVulnerableController(PersonaVulnerableService personaVulnerableService) {
        this.personaVulnerableService = personaVulnerableService;
    }

    @PostMapping(path = "/personas-vulnerables", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createPersonaVulnerable(@RequestBody PersonaVulnerableDTO personaVulnerableDTO) {
        personaVulnerableService.save(personaVulnerableDTO);
        return new ResponseEntity<>("Persona vulnerable creada correctamente!", HttpStatus.OK);
    }

    @GetMapping(path = "/personas-vulnerables", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<PersonaVulnerable>> getPersonasVulnerables() {
        List<PersonaVulnerable> personasVulnerables = personaVulnerableService.findAll();
        return new ResponseEntity<>(personasVulnerables, HttpStatus.OK);
    }
}
