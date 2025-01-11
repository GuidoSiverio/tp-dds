package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.PersonaVulnerable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "RegistroPersonaVulnerable")
public class RegistroPersonaVulnerable extends Contribucion {

    @OneToOne
    private PersonaVulnerable personaVulnerable;

    public RegistroPersonaVulnerable(PersonaVulnerable personaVulnerable) {
        this.personaVulnerable = personaVulnerable;
    }

    public RegistroPersonaVulnerable() {
    }
}
