package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.dto.PersonaVulnerableDTO;
import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import frba.utn.edu.ar.tp_dds.repositories.PersonaVulnerableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaVulnerableService {

    private final PersonaVulnerableRepository personaVulnerableRepository;
    private final TarjetaService tarjetaService;

    public PersonaVulnerableService(PersonaVulnerableRepository personaVulnerableRepository, TarjetaService tarjetaService) {
        this.personaVulnerableRepository = personaVulnerableRepository;
        this.tarjetaService = tarjetaService;
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
        save(personaVulnerable);
    }

    public void save(PersonaVulnerable personaVulnerable) {
        personaVulnerableRepository.save(personaVulnerable);
    }

    public List<PersonaVulnerable> findAll() {
        return personaVulnerableRepository.findAll();
    }
}
