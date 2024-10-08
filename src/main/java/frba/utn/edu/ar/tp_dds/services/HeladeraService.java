package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.repositories.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

  private final BaseRepository<Heladera, Long> heladeraRepository;

  public HeladeraService(BaseRepository<Heladera, Long> heladeraRepository) {
    this.heladeraRepository = heladeraRepository;
  }

  public void save(Heladera heladera) {
    heladeraRepository.save(heladera);
  }

  public List<Heladera> findAll() {
    return heladeraRepository.findAll();
  }
}
