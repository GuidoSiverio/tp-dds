package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.OfertaDTO;
import frba.utn.edu.ar.tp_dds.entities.Oferta;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.OfertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final ColaboradorRepository colaboradorRepository;

    public OfertaService(OfertaRepository ofertaRepository, ColaboradorRepository colaboradorRepository) {
        this.ofertaRepository = ofertaRepository;
        this.colaboradorRepository = colaboradorRepository;
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

    public void agregarOferta(Long id, Long colaboradorId) {
        ofertaRepository.findById(id).ifPresent(oferta -> {
            colaboradorRepository.findById(colaboradorId).ifPresent(colaborador ->  {
                colaborador.add(oferta);
                save(oferta);
            });;
        });
    }

    public List<Oferta> obtenerOfertasDisponibles(Long colaboradorId) {

        Colaborador colaborador = colaboradorRepository.findById(colaboradorId).get();

        return ofertaRepository.findAll().stream().filter(oferta -> {
            return !colaborador.getOfertas().contains(oferta);
        }).toList();

    }
}