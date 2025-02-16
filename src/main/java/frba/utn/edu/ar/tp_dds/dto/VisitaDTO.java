package frba.utn.edu.ar.tp_dds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitaDTO {

    private String comentario;
    private Boolean solucionado;
    private String imagen;
    private Long tecnicoId;
    private Long heladeraId;
    private Long incidenteId;

}
