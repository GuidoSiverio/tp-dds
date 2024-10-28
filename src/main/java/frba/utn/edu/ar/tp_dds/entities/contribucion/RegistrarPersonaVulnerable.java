package frba.utn.edu.ar.tp_dds.entities.contribucion;

import frba.utn.edu.ar.tp_dds.entities.tarjeta.Tarjeta;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "RegistroPersonaVulnerable")
public class RegistrarPersonaVulnerable extends Contribucion {

    //private List<Tarjeta> tarjetas;

    public void realizar() {

    }

}
