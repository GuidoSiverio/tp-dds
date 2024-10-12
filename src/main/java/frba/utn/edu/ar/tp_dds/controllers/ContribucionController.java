package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DistribucionVianda;
import frba.utn.edu.ar.tp_dds.services.ContribucionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ContribucionController {

    private final ContribucionService contribucionService;

    public ContribucionController(ContribucionService contribucionService) {
        this.contribucionService = contribucionService;
    }

    @PostMapping(path = "/contribuciones/distribucion", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> distribuirViandas(@RequestBody DistribucionVianda distribucionVianda) {
        contribucionService.validarEspacioHeladeras(distribucionVianda.getOrigen(), distribucionVianda.getDestino(), distribucionVianda.getCantidadViandas());
        contribucionService.distribuirViandas(distribucionVianda);
        return new ResponseEntity<>("Viandas distribuidas correctamente!", HttpStatus.OK);
    }

    @PostMapping(path = "/contribuciones/donacion-vianda", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> donarVianda(@RequestBody ViandaDTO viandaDTO) {
        contribucionService.donarVianda(viandaDTO);
        return new ResponseEntity<>("Vianda donada correctamente!", HttpStatus.OK);
    }
}