package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitaDTO {

    private String comentario;
    private Boolean solucionado;
    private Long tecnicoId;
    private Long heladeraId;
    private Long incidenteId;

    public VisitaDTO() {
    }

    public VisitaDTO(String comentarios, Boolean solucionado, Long tecnicoId, Long heladeraId, Long incidenteId) {
        this.comentario = comentarios;
        this.solucionado = solucionado;
        this.tecnicoId = tecnicoId;
        this.heladeraId = heladeraId;
        this.incidenteId = incidenteId;
    }

}
