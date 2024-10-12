package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.ViandaDTO;
import frba.utn.edu.ar.tp_dds.entities.Heladera;
import frba.utn.edu.ar.tp_dds.entities.Vianda;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

  private final HeladeraRepository heladeraRepository;

  public HeladeraService(HeladeraRepository heladeraRepository) {
    this.heladeraRepository = heladeraRepository;
  }

  public void save(Heladera heladera) {
    heladera.setViandas(new ArrayList<>());
    heladeraRepository.save(heladera);
  }

  public List<Heladera> findAll() {
    return heladeraRepository.findAll();
  }

  public Optional<Heladera> findById(Long id) {
    return heladeraRepository.findById(id);
  }
}
