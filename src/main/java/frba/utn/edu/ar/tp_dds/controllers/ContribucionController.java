package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.*;
import frba.utn.edu.ar.tp_dds.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ContribucionController {

    private final DistribucionViandaService distribucionViandaService;
    private final DonacionViandaService donacionViandaService;
    private final RegistroPersonaVulnerableService registroPersonaVulnerableService;
    private final RegistroOfertaService registroOfertaService;
    private final DonacionDineroService donacionDineroService;

    public ContribucionController(DistribucionViandaService distribucionViandaService, DonacionViandaService donacionViandaService, RegistroPersonaVulnerableService registroPersonaVulnerableService, RegistroOfertaService registroOfertaService, DonacionDineroService donacionDineroService) {
        this.distribucionViandaService = distribucionViandaService;
        this.donacionViandaService = donacionViandaService;
        this.registroPersonaVulnerableService = registroPersonaVulnerableService;
        this.registroOfertaService = registroOfertaService;
        this.donacionDineroService = donacionDineroService;
    }

    @PostMapping(path = "/contribuciones/distribucion", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> distribuirViandas(@RequestBody DistribucionViandaDTO distribucionViandaDTO) {
        distribucionViandaService.validarEspacioHeladeras(distribucionViandaDTO.getHeladeraOrigen(), distribucionViandaDTO.getHeladeraDestino(), distribucionViandaDTO.getCantidadViandas());
        distribucionViandaService.distribuirViandas(distribucionViandaDTO);
        return new ResponseEntity<>("Viandas distribuidas correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/donacion-vianda", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> donarVianda(@RequestBody ViandaDTO viandaDTO) {
        donacionViandaService.donarVianda(viandaDTO);
        return new ResponseEntity<>("Vianda donada correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/donacion-dinero", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> donarDinero(@RequestBody DonacionDineroDTO donacionDineroDTO) {
        donacionDineroService.donarDinero(donacionDineroDTO);
        return new ResponseEntity<>("Dinero donado correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/personas-vulnerables", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registrarPersonaVulnerable(@RequestBody PersonaVulnerableDTO personaVulnerableDTO) {
        registroPersonaVulnerableService.registrar(personaVulnerableDTO);
        return new ResponseEntity<>("Persona vulnerable registrada correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/ofertas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registroOferta(@RequestBody OfertaDTO ofertaDTO) {
        registroOfertaService.registrar(ofertaDTO);
        return new ResponseEntity<>("Oferta registrada correctamente!", HttpStatus.OK);
    }

}
