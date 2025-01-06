package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.DistribucionViandaDTO;
import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContribucionService {

    private final HeladeraService heladeraService;
    private final ViandaService viandaService;
    private final ColaboradorService colaboradorService;
    private final TarjetaService tarjetaService;
    private final PersonaVulnerableService personaVulnerableService;

    public ContribucionService(HeladeraService heladeraService, ViandaService viandaService, ColaboradorService colaboradorService, TarjetaService tarjetaService, PersonaVulnerableService personaVulnerableService) {
        this.heladeraService = heladeraService;
        this.viandaService = viandaService;
        this.colaboradorService = colaboradorService;
        this.tarjetaService = tarjetaService;
        this.personaVulnerableService = personaVulnerableService;
    }

}
