package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import org.springframework.stereotype.Service;

@Service
public class RegistroOfertaService {

    private final OfertaService ofertaService;

    public RegistroOfertaService(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    public void registrar(OfertaDTO ofertaDTO) {
        ofertaService.save(ofertaDTO);
    }
}
