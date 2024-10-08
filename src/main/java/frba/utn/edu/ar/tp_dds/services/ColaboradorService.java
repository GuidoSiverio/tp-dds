package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.ColabordaorRepository;
import java.util.List;
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
}
