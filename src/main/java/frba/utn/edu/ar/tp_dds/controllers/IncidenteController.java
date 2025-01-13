package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import frba.utn.edu.ar.tp_dds.services.IncidenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class IncidenteController {

    private final IncidenteService incidenteService;

    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @PostMapping(path = "/incidentes", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createIncidente(@RequestBody IncidenteDTO incidenteDTO) {
        incidenteService.save(incidenteDTO);
        return new ResponseEntity<>("Incidente creado correctamente!", HttpStatus.OK);
    }
}
