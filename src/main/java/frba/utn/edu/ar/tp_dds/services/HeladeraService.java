package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.HeladeraDTO;
import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import frba.utn.edu.ar.tp_dds.repositories.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

  private final HeladeraRepository heladeraRepository;
  private final ColaboradorRepository colaboradorRepository;
  private final IncidenteService incidenteService;
  private final IncidenteRepository incidenteRepository;

  @Autowired
  private SuscriptorService suscriptorService;

  public HeladeraService(HeladeraRepository heladeraRepository, ColaboradorRepository colaboradorRepository, IncidenteService incidenteService, IncidenteRepository incidenteRepository) {
    this.heladeraRepository = heladeraRepository;
      this.colaboradorRepository = colaboradorRepository;
      this.incidenteService = incidenteService;
      this.incidenteRepository = incidenteRepository;
  }

  public void save(HeladeraDTO heladeraDTO) {
    Heladera heladera = new Heladera(heladeraDTO);
    colaboradorRepository.findById(heladeraDTO.getColaboradorId()).ifPresent(colaborador ->  {
      colaborador.add(heladera);
    });
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

  public void suscribirse(Long id, Long colaboradorId) {
    heladeraRepository.findById(id).ifPresent(heladera -> {
      colaboradorRepository.findById(colaboradorId).ifPresent(colaborador -> {
        suscriptorService.agregarSuscriptor(heladera.getId(), colaborador);
      });
    });
  }

  public void reportarFalla(Long id, IncidenteDTO incidenteDTO) {
    Incidente incidente = incidenteService.createIncidente(incidenteDTO);

    heladeraRepository.findById(incidenteDTO.getHeladeraId()).ifPresent(heladera -> {
      heladera.registrar(incidente);
      heladera.setActiva(false);
      heladera.notificarEvento(suscriptorService,"Se reportó un incidente en la heladera " + heladera.getNombre());
    });

    if (incidenteDTO.getColaboradorId() != null){
      colaboradorRepository.findById(incidenteDTO.getColaboradorId()).ifPresent(colaborador -> {
        if (colaborador instanceof PersonaHumana personaHumana) {
          personaHumana.add(incidente);
        } else {
          throw new IllegalArgumentException("El colaborador no es una PersonaHumana");
        }
      });
    }
    incidenteRepository.save(incidente);
  }

  public void notificarIncidente(Long id, Incidente incidente, String mensaje) {
    heladeraRepository.findById(id).ifPresent(heladera -> {
      heladera.registrar(incidente);
      heladera.setActiva(false);
      heladera.notificarEvento(suscriptorService, mensaje);
    });
  }

  public void desuscribirse(Long heladeraId, Long colaboradorId) {
    heladeraRepository.findById(heladeraId).ifPresent(heladera -> {
      colaboradorRepository.findById(colaboradorId).ifPresent(colaborador -> {
        suscriptorService.eliminarSuscriptor(heladera.getId(), colaborador);
      });
    });
  }

  public List<Incidente> getIncidentes(Long id) {
    return heladeraRepository.findById(id).map(heladera -> heladera.getIncidentes().stream().filter(incidente -> !incidente.isEstadoResuelta()).toList()).orElseThrow();
  }
}
