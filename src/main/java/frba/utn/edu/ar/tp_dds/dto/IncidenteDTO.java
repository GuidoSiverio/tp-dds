package frba.utn.edu.ar.tp_dds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncidenteDTO {

    private String descripcion;
    private String foto;
    private String tipoAlerta;
    private Long heladeraId;
    private Long colaboradorId;

}
