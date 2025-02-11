package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.VisitaDTO;
import frba.utn.edu.ar.tp_dds.entities.Visita;
import frba.utn.edu.ar.tp_dds.entities.incidente.Alerta;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;
import frba.utn.edu.ar.tp_dds.repositories.IncidenteRepository;
import frba.utn.edu.ar.tp_dds.repositories.TecnicoRepository;
import frba.utn.edu.ar.tp_dds.repositories.VisitaRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitaService {

    private final VisitaRepository visitaRepository;
    private final HeladeraRepository heladeraRepository;
    private final TecnicoRepository tecnicoRepository;
    private final IncidenteRepository incidenteRepository;
    private final RabbitProducerService rabbitProducerService;

    public VisitaService(VisitaRepository visitaRepository, HeladeraRepository heladeraRepository, TecnicoRepository tecnicoRepository, IncidenteRepository incidenteRepository, RabbitProducerService rabbitProducerService) {
        this.visitaRepository = visitaRepository;
        this.heladeraRepository = heladeraRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.incidenteRepository = incidenteRepository;
        this.rabbitProducerService = rabbitProducerService;
    }


    public void registrarVisita(VisitaDTO visitaDTO) {
        Visita visita = new Visita(visitaDTO);

        tecnicoRepository.findById(visitaDTO.getTecnicoId()).ifPresent(tecnico -> {
            tecnico.add(visita);
        });

        incidenteRepository.findById(visitaDTO.getIncidenteId()).ifPresent(incidente -> {
            incidente.add(visita);
            if (visitaDTO.getSolucionado()) {
                incidente.setEstadoResuelta(true);
                if (incidente instanceof Alerta alerta){
                    if (alerta.getTipoAlerta().equals("Falla en la conexion")){
                        rabbitProducerService.getHeladerasConFallaDeConexion().remove(incidente.getHeladera().getId());
                    } else if (alerta.getTipoAlerta().equals("Fraude")){
                        rabbitProducerService.getHeladerasConFraude().remove(incidente.getHeladera().getId());
                    }
                }
            }
        });

        if (visitaDTO.getSolucionado()){
            heladeraRepository.findById(visitaDTO.getHeladeraId()).ifPresent(heladera -> {
                if (heladera.getIncidentes().stream().allMatch(Incidente::isEstadoResuelta)){
                    heladera.setActiva(true);
                }
            });
        }
        save(visita);
    }

    private void save(Visita visita) {
        visitaRepository.save(visita);
    }

    public void deleteVisitsByTecnicoId(Long id) {
        visitaRepository.deleteByTecnicoId(id);
    }
}
