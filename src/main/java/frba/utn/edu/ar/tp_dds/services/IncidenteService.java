package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaHumana;
import frba.utn.edu.ar.tp_dds.entities.colaborador.PersonaJuridica;
import frba.utn.edu.ar.tp_dds.entities.incidente.Alerta;
import frba.utn.edu.ar.tp_dds.entities.incidente.FallaTecnica;
import frba.utn.edu.ar.tp_dds.entities.incidente.Incidente;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;
import frba.utn.edu.ar.tp_dds.repositories.IncidenteRepository;
import org.springframework.stereotype.Service;

@Service
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;
    private final HeladeraRepository heladeraRepository;
    private final ColaboradorRepository colaboradorRepository;

    public IncidenteService(IncidenteRepository incidenteRepository, HeladeraRepository heladeraRepository, ColaboradorRepository colaboradorRepository) {
        this.incidenteRepository = incidenteRepository;
        this.heladeraRepository = heladeraRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public Incidente createIncidente(IncidenteDTO incidenteDTO) {
        if (incidenteDTO.getTipoAlerta() != null) {
            return new Alerta(incidenteDTO);
        } else if (incidenteDTO.getDescripcion() != null) {
            return new FallaTecnica(incidenteDTO);
        }
        throw new IllegalArgumentException("No se pudo crear el incidente");
    }

    public void save(IncidenteDTO incidenteDTO){
        Incidente incidente = createIncidente(incidenteDTO);

        heladeraRepository.findById(incidenteDTO.getHeladeraId()).ifPresent(heladera -> {
            heladera.registrar(incidente);
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
        save(incidente);
    }

    public void save(Incidente incidente) {
        incidenteRepository.save(incidente);
    }

}
