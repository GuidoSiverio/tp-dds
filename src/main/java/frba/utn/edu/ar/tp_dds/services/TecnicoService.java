package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.TecnicoDTO;
import frba.utn.edu.ar.tp_dds.entities.Tecnico;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public void save(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        save(tecnico);
    }

    public void save(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    public void update(Long id, TecnicoDTO tecnicoDTO) {
        tecnicoRepository.findById(id).ifPresentOrElse(t -> {
            t.update(tecnicoDTO);
            save(t);
        }, () -> {
            throw new RuntimeException("Tecnico no encontrado");
        });
    }

    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
