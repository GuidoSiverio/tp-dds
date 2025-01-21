package frba.utn.edu.ar.tp_dds.services;

import org.springframework.stereotype.Service;

@Service
public class ContribucionService {

    private final HeladeraService heladeraService;
    private final ViandaService viandaService;
    private final ColaboradorService colaboradorService;
    private final TarjetaService tarjetaService;
    private final RegistroPersonaVulnerableService registroPersonaVulnerableService;

    public ContribucionService(HeladeraService heladeraService, ViandaService viandaService, ColaboradorService colaboradorService, TarjetaService tarjetaService, RegistroPersonaVulnerableService registroPersonaVulnerableService) {
        this.heladeraService = heladeraService;
        this.viandaService = viandaService;
        this.colaboradorService = colaboradorService;
        this.tarjetaService = tarjetaService;
        this.registroPersonaVulnerableService = registroPersonaVulnerableService;
    }

}
