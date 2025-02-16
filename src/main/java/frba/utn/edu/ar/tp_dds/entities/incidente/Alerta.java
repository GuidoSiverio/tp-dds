package frba.utn.edu.ar.tp_dds.entities.incidente;

import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("Alerta")
public class Alerta extends Incidente {

    private String tipoAlerta; // Temperatura, Fraude, Falla conexión

    public Alerta(IncidenteDTO incidenteDTO) {
        super(LocalDateTime.now(), false);
        this.tipoAlerta = incidenteDTO.getTipoAlerta();
    }

    public Alerta() {
    }

    public Alerta(String tipoAlerta) {
        super(LocalDateTime.now(), false);
        this.tipoAlerta = tipoAlerta;
    }
}
