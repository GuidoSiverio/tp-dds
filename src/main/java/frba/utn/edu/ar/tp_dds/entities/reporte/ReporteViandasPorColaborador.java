package frba.utn.edu.ar.tp_dds.entities.reporte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteViandasPorColaborador {

    private String colaborador;
    private Long cantidadViandas;

    public ReporteViandasPorColaborador(String colaborador, Long cantidadViandas) {
        this.colaborador = colaborador;
        this.cantidadViandas = cantidadViandas;
    }
}
