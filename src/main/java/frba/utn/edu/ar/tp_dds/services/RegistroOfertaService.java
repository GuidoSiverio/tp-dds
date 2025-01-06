package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.entities.Oferta;
import frba.utn.edu.ar.tp_dds.entities.contribucion.RegistroOferta;
import frba.utn.edu.ar.tp_dds.repositories.RegistroOfertaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroOfertaService {

    private final OfertaService ofertaService;
    private final RegistroOfertaRepository registroOfertaRepository;

    public RegistroOfertaService(OfertaService ofertaService, RegistroOfertaRepository registroOfertaRepository) {
        this.ofertaService = ofertaService;
        this.registroOfertaRepository = registroOfertaRepository;
    }

    public void registrar(OfertaDTO ofertaDTO) {
        Oferta oferta = new Oferta(ofertaDTO);
        ofertaService.save(oferta);
        RegistroOferta registroOferta = new RegistroOferta();
        registroOferta.setOferta(oferta);
        registroOfertaRepository.save(registroOferta);
    }
}
