package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.entities.Oferta;
import frba.utn.edu.ar.tp_dds.entities.contribucion.RegistroOferta;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.RegistroOfertaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroOfertaService {

    private final OfertaService ofertaService;
    private final RegistroOfertaRepository registroOfertaRepository;
    private final ColaboradorRepository colaboradorRepository;

    public RegistroOfertaService(OfertaService ofertaService, RegistroOfertaRepository registroOfertaRepository, ColaboradorRepository colaboradorRepository) {
        this.ofertaService = ofertaService;
        this.registroOfertaRepository = registroOfertaRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void registrar(OfertaDTO ofertaDTO) {
        Oferta oferta = new Oferta(ofertaDTO);
        ofertaService.save(oferta);
        RegistroOferta registroOferta = new RegistroOferta();
        registroOferta.setOferta(oferta);
        colaboradorRepository.findById(ofertaDTO.getColaboradorId()).ifPresent(colaborador -> {
            colaborador.add(registroOferta);
        });
        save(registroOferta);
    }

    private void save(RegistroOferta registroOferta) {
        registroOfertaRepository.save(registroOferta);
    }

}
