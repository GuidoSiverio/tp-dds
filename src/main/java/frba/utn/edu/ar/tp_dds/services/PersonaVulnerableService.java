package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.repositories.PersonaVulnerableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaVulnerableService {

    private final PersonaVulnerableRepository personaVulnerableRepository;

    public PersonaVulnerableService(PersonaVulnerableRepository personaVulnerableRepository) {
        this.personaVulnerableRepository = personaVulnerableRepository;
    }

    public void save(PersonaVulnerableDTO personaVulnerableDTO) {
        PersonaVulnerable personaVulnerable = new PersonaVulnerable(personaVulnerableDTO);
        save(personaVulnerable);
    }

    public void save(PersonaVulnerable personaVulnerable) {
        personaVulnerableRepository.save(personaVulnerable);
    }

    public List<PersonaVulnerable> findAll() {
        return personaVulnerableRepository.findAll();
    }
}
