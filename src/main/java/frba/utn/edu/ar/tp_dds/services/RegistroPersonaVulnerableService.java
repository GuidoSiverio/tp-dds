package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.contribucion.RegistroPersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.repositories.PersonaVulnerableRepository;
import frba.utn.edu.ar.tp_dds.repositories.RegistroPersonaVulnerableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroPersonaVulnerableService {

    private final PersonaVulnerableRepository personaVulnerableRepository;
    private final RegistroPersonaVulnerableRepository registroPersonaVulnerableRepository;
    private final TarjetaService tarjetaService;
    private final ColaboradorRepository colaboradorRepository;

    public RegistroPersonaVulnerableService(PersonaVulnerableRepository personaVulnerableRepository, RegistroPersonaVulnerableRepository registroPersonaVulnerableRepository, TarjetaService tarjetaService, ColaboradorRepository colaboradorRepository) {
        this.personaVulnerableRepository = personaVulnerableRepository;
        this.registroPersonaVulnerableRepository = registroPersonaVulnerableRepository;
        this.tarjetaService = tarjetaService;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void registrar(PersonaVulnerableDTO personaVulnerableDTO) {
        List<Tarjeta> tarjetas = tarjetaService.findTarjetasDisponibles();

        tarjetas.stream().findFirst().ifPresentOrElse(tarjeta -> {
            PersonaVulnerable personaVulnerable = new PersonaVulnerable(personaVulnerableDTO);
            personaVulnerable.setTarjeta(tarjeta);
            tarjeta.registrarAsignacion();
            save(personaVulnerable);
            tarjetaService.save(tarjeta);
        }, () -> {
            throw new RuntimeException("No hay tarjetas disponibles");
        });
    }

    public void save(PersonaVulnerableDTO personaVulnerableDTO) {
        PersonaVulnerable personaVulnerable = new PersonaVulnerable(personaVulnerableDTO);
        try {
            Tarjeta tarjetaDisponible = tarjetaService.findTarjeta(personaVulnerableDTO.getTarjeta()).get(0);
            personaVulnerable.setTarjeta(tarjetaDisponible);
            tarjetaDisponible.registrarAsignacion();
            tarjetaDisponible.setPersonaVulnerable(personaVulnerable);
        } catch (Exception e) {
            throw new RuntimeException("No se encuentra la tarjeta con código " + personaVulnerableDTO.getTarjeta());
        }
        save(personaVulnerable);

        colaboradorRepository.findById(personaVulnerableDTO.getColaboradorId()).ifPresent(colaborador -> {
            RegistroPersonaVulnerable registroPersonaVulnerable = new RegistroPersonaVulnerable(personaVulnerable);
            colaborador.add(registroPersonaVulnerable);
            save(registroPersonaVulnerable);
        });
    }

    public void save(PersonaVulnerable personaVulnerable) {
        personaVulnerableRepository.save(personaVulnerable);
    }

    public void save(RegistroPersonaVulnerable registroPersonaVulnerable) {
        registroPersonaVulnerableRepository.save(registroPersonaVulnerable);
    }

    public List<PersonaVulnerable> findAll() {
        return personaVulnerableRepository.findAll();
    }
}
