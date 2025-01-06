package frba.utn.edu.ar.tp_dds.controllers;

import frba.utn.edu.ar.tp_dds.dto.TarjetaDTO;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.services.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TarjetaController {

    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping(path = "/tarjetas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createTarjeta(@RequestBody TarjetaDTO tarjetaDTO) {
        tarjetaService.save(tarjetaDTO);
        return new ResponseEntity<>("Tarjeta creada correctamente!", HttpStatus.OK);
    }

    @GetMapping(path = "/tarjetas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Tarjeta>> getTarjetas() {
        List<Tarjeta> tarjetas = tarjetaService.findAll();
        return new ResponseEntity<>(tarjetas, HttpStatus.OK);
    }
}
