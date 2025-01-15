package frba.utn.edu.ar.tp_dds.entities.incidente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("FallaTecnica")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FallaTecnica extends Incidente {

    private String descripcion;
    private String foto;

    public FallaTecnica(IncidenteDTO incidenteDTO) {
        super(LocalDateTime.now(), false);
        this.descripcion = incidenteDTO.getDescripcion();
        this.foto = incidenteDTO.getFoto();
    }

    public FallaTecnica() {
    }
}



