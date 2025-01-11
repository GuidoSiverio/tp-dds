package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DistribucionViandaDTO {

    private Long heladeraOrigen;
    private Long heladeraDestino;
    private int cantidadViandas;
    private String motivoDistribucion;
    private LocalDateTime fechaDistribucion;
    private Long colaboradorId;
}
