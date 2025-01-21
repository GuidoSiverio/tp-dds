package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

  private final HeladeraRepository heladeraRepository;

  public HeladeraService(HeladeraRepository heladeraRepository) {
    this.heladeraRepository = heladeraRepository;
  }

  public void save(HeladeraDTO heladeraDTO) {
    Heladera heladera = new Heladera(heladeraDTO);
    save(heladera);
  }

  public void save(Heladera heladera) {
    heladeraRepository.save(heladera);
  }

  public List<Heladera> findAll() {
    return heladeraRepository.findAll();
  }

  public Optional<Heladera> findById(Long id) {
    return heladeraRepository.findById(id);
  }

  public void update(Long id, HeladeraDTO heladeraDTO) {
    heladeraRepository.findById(id).ifPresentOrElse(heladera -> {
      if (heladeraDTO.getLongitud() != null){
        heladera.setLongitud(heladeraDTO.getLongitud());
      }
      if (heladeraDTO.getLatitud() != null){
        heladera.setLatitud(heladeraDTO.getLatitud());
      }
      if (heladeraDTO.getDireccion() != null){
        heladera.setDireccion(heladeraDTO.getDireccion());
      }
      if (heladeraDTO.getNombre() != null){
        heladera.setNombre(heladeraDTO.getNombre());
      }
      if (heladeraDTO.getCapacidad() != null){
        heladera.setCapacidad(heladeraDTO.getCapacidad());
      }
      if (heladeraDTO.getFechaFuncionamiento() != null){
        heladera.setFechaFuncionamiento(LocalDateTime.parse(heladeraDTO.getFechaFuncionamiento()));
      }
      save(heladera);
    }, () -> {
      throw new RuntimeException("Heladera no encontrada con id: " + id);
    });
  }

  public void delete(Long id) {
    heladeraRepository.deleteById(id);
  }

  public Double getHeladerasActivas(Long id) {
    return 0.00; //heladeraRepository.getHeladerasActivas(id);
  }

  public Double getSumMesesActivas(Long id) {
    return 0.00; // heladeraRepository.getSumMesesActivas(id);
  }
}
