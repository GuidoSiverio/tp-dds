package frba.utn.edu.ar.tp_dds.entities.reporte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteFallasPorHeladera {

    private String heladera;
    private Long cantidadFallas;

    public ReporteFallasPorHeladera(String heladera, Long cantidadFallas) {
        this.heladera = heladera;
        this.cantidadFallas = cantidadFallas;
    }
}
