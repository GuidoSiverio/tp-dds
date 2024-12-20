package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.DistribucionViandaDTO;
import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ContribucionController {

    private final ContribucionService contribucionService;
    private final DistribucionViandaService distribucionViandaService;
    private final DonacionViandaService donacionViandaService;
    private final PersonaVulnerableService personaVulnerableService;
    private final RegistroOfertaService registroOfertaService;

    public ContribucionController(ContribucionService contribucionService, DistribucionViandaService distribucionViandaService, DonacionViandaService donacionViandaService, PersonaVulnerableService personaVulnerableService, RegistroOfertaService registroOfertaService) {
        this.contribucionService = contribucionService;
        this.distribucionViandaService = distribucionViandaService;
        this.donacionViandaService = donacionViandaService;
        this.personaVulnerableService = personaVulnerableService;
        this.registroOfertaService = registroOfertaService;
    }

    @PostMapping(path = "/contribuciones/distribucion", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> distribuirViandas(@RequestBody DistribucionViandaDTO distribucionViandaDTO) {
        distribucionViandaService.validarEspacioHeladeras(distribucionViandaDTO.getOrigenId(), distribucionViandaDTO.getDestinoId(), distribucionViandaDTO.getCantidadViandas());
        distribucionViandaService.distribuirViandas(distribucionViandaDTO);
        return new ResponseEntity<>("Viandas distribuidas correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/donacion-vianda", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> donarVianda(@RequestBody ViandaDTO viandaDTO) {
        donacionViandaService.donarVianda(viandaDTO);
        return new ResponseEntity<>("Vianda donada correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/personas-vulnerables", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registrarPersonaVulnerable(@RequestBody PersonaVulnerableDTO personaVulnerableDTO) {
        personaVulnerableService.registrar(personaVulnerableDTO);
        return new ResponseEntity<>("Persona vulnerable registrada correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/ofertas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registroOferta(@RequestBody OfertaDTO ofertaDTO) {
        registroOfertaService.registrar(ofertaDTO);
        return new ResponseEntity<>("Oferta registrada correctamente!", HttpStatus.OK);
    }

}
