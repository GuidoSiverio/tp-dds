package frba.utn.edu.ar.tp_dds.entities.incidente;

import frba.utn.edu.ar.tp_dds.dto.IncidenteDTO;
import frba.utn.edu.ar.tp_dds.entities.colaborador.Colaborador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("FALLA_TECNICA")
public class FallaTecnica extends Incidente {

    private String descripcion;
    private String foto;

    @ManyToOne
    private Colaborador colaborador;

    public FallaTecnica(IncidenteDTO incidenteDTO) {
        super(LocalDateTime.now(), false);
        this.descripcion = incidenteDTO.getDescripcion();
        this.foto = incidenteDTO.getFoto();
    }

}

