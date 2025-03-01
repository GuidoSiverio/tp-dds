package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DonacionDineroDTO {

    private Double monto;
    private String frecuencia;
    private boolean formaPeriodica;
    private Long colaboradorId;
}
