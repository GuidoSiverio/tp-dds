package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.services.ContribucionService;
import frba.utn.edu.ar.tp_dds.services.HeladeraService;
import frba.utn.edu.ar.tp_dds.services.ViandaService;
import org.springframework.stereotype.Component;

@Component
public class Reconocimiento {

    private static final double PESOS_DONADOS_COEF = 0.5;
    private static final double VIANDAS_DISTRIBUIDAS_COEF = 1.0;
    private static final double VIANDAS_DONADAS_COEF = 1.5;
    private static final double TARJETAS_REPARTIDAS_COEF = 2.0;
    private static final double HELADERAS_ACTIVAS_COEF = 5.0;

    private ViandaService viandaService;
    private ContribucionService contribucionService;
    private HeladeraService heladeraService;

    public Reconocimiento(ViandaService viandaService, ContribucionService contribucionService, HeladeraService heladeraService) {
        this.viandaService = viandaService;
        this.contribucionService = contribucionService;
        this.heladeraService = heladeraService;
    }

    public Reconocimiento() {}

    public Double getPoints(Long id){

        Double pesosDonados = viandaService.getPesosDonados(id);
        Double viandasDistribuidas = contribucionService.getViandasDistribuidas(id);
        Double viandasDonadas = viandaService.getViandasDonadas(id);
        Double tarjetasRepartidas = contribucionService.getTarjetasRepartidas(id);
        Double heladerasActivas = heladeraService.getHeladerasActivas(id);
        Double sumMesesActivas = heladeraService.getSumMesesActivas(id);

        Double totalPoints = pesosDonados * PESOS_DONADOS_COEF
                + viandasDistribuidas * VIANDAS_DISTRIBUIDAS_COEF
                + viandasDonadas * VIANDAS_DONADAS_COEF
                + tarjetasRepartidas * TARJETAS_REPARTIDAS_COEF
                + heladerasActivas * sumMesesActivas * HELADERAS_ACTIVAS_COEF;


        return totalPoints;
    }

}
