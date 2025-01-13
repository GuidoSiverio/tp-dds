package frba.utn.edu.ar.tp_dds.entities.incidente;

import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ALERTA")
public class Alerta extends Incidente {

    private String tipoAlerta; // Temperatura, Fraude, Falla conexión

    public Alerta(IncidenteDTO incidenteDTO) {
        super(LocalDateTime.now(), false);
        this.tipoAlerta = incidenteDTO.getTipoAlerta();
    }
}
