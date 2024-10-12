package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.contribucion.DonacionVianda;
import frba.utn.edu.ar.tp_dds.repositories.ViandaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ViandaService {

  private final ViandaRepository viandaRepository;

  public ViandaService(ViandaRepository viandaRepository) {
    this.viandaRepository = viandaRepository;
  }

  public void save(Vianda vianda) {
    viandaRepository.save(vianda);
  }

  public List<Vianda> findAll() {
    return viandaRepository.findAll();
  }

}
