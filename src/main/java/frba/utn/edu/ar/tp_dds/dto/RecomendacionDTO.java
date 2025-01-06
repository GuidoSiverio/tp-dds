package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecomendacionDTO {
    private String nombre;
    private double latitud;
    private double longitud;

    public RecomendacionDTO() {
    }

    public RecomendacionDTO(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}