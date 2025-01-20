package frba.utn.edu.ar.tp_dds.entities.reporte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteViandasPorHeladera {

    private String heladera;
    private Long cantidadViandas;

    public ReporteViandasPorHeladera(String heladera, Long cantidadViandas) {
        this.heladera = heladera;
        this.cantidadViandas = cantidadViandas;
    }
}
