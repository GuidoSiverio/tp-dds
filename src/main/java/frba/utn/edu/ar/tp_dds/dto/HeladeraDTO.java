package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeladeraDTO {

    private String longitud;
    private String latitud;
    private String direccion;
    private String nombre;
    private Integer capacidad;
    private String radio;
    private String lugarRecomendado;
    private String tempMinAceptable;
    private String tempMaxAceptable;
    private Long colaboradorId;

}
