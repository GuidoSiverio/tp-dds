package frba.utn.edu.ar.tp_dds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecomendacionRequest {
    private double latitud;
    private double longitud;
    private double radio;

    // Constructor vacío
    public RecomendacionRequest() {
    }

    // Constructor con parámetros
    public RecomendacionRequest(double latitud, double longitud, double radio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }
}
