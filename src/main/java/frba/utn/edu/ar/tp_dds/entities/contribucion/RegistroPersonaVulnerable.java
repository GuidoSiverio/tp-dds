package frba.utn.edu.ar.tp_dds.entities.contribucion;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "RegistroPersonaVulnerable")
public class RegistroPersonaVulnerable extends Contribucion {

    //private List<Tarjeta> tarjetas;

    public void realizar() {

    }

}
