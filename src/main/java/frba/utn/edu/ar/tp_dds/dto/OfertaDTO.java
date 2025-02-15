package frba.utn.edu.ar.tp_dds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OfertaDTO {

    private String nombre;
    private String rubro;
    private Double puntosNecesarios;
    private String imagen;
    private Long colaboradorId;
}
