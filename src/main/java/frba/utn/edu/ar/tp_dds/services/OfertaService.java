package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.entities.Oferta;
import frba.utn.edu.ar.tp_dds.repositories.OfertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public void save(OfertaDTO ofertaDTO){
        save(new Oferta(ofertaDTO));
    }

    public void save(Oferta oferta){
        ofertaRepository.save(oferta);
    }

    public List<Oferta> findAll(){
        return ofertaRepository.findAll();
    }
}
