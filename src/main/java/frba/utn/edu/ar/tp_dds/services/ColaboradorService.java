package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.ColabordaorRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    private final ColabordaorRepository colaboradorRepository;

    public ColaboradorService(ColabordaorRepository colaboradorRepository) {
      this.colaboradorRepository = colaboradorRepository;
    }

    public void save(Colaborador colaborador) {
      colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> findAll() {
    return colaboradorRepository.findAll();
    }

    public void delete(Long id) {
        colaboradorRepository.deleteById(id);
    }

    public Optional<Colaborador> findById(Long id) {
        return colaboradorRepository.findById(id);
    }

    public void update(Long id, Colaborador colaborador) {
        Optional<Colaborador> foundColaborador = colaboradorRepository.findById(id);
        if (foundColaborador.isPresent()) {
            colaborador.setId(foundColaborador.get().getId());
            colaboradorRepository.save(colaborador);
        } else {
            throw new RuntimeException("Colaborador no encontrado con id: " + id);
    }
  }
}
