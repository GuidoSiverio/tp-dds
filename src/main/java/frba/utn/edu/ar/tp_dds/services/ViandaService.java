package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.repositories.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ViandaService {

  private final BaseRepository<Vianda, Long> viandaRepository;

  public ViandaService(BaseRepository<Vianda, Long> viandaRepository) {
    this.viandaRepository = viandaRepository;
  }

  public void save(Vianda vianda) {
    viandaRepository.save(vianda);
  }

  public List<Vianda> findAll() {
    return viandaRepository.findAll();
  }


}
