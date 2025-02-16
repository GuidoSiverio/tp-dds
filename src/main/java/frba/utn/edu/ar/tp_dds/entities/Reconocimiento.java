package frba.utn.edu.ar.tp_dds.entities;

import frba.utn.edu.ar.tp_dds.repositories.ColaboradorRepository;
import frba.utn.edu.ar.tp_dds.services.*;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class Reconocimiento {

    private static final double PESOS_DONADOS_COEF = 0.5;
    private static final double VIANDAS_DISTRIBUIDAS_COEF = 1.0;
    private static final double VIANDAS_DONADAS_COEF = 1.5;
    private static final double TARJETAS_REPARTIDAS_COEF = 2.0;
    private static final double HELADERAS_ACTIVAS_COEF = 5.0;

    private final DonacionDineroService donacionDineroService;
    private final DistribucionViandaService distribucionViandaService;
    private final DonacionViandaService donacionViandaService;
    private final TarjetaService tarjetaService;
    private final HeladeraService heladeraService;
    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public Reconocimiento(
            DonacionDineroService donacionDineroService,
            DistribucionViandaService distribucionViandaService,
            DonacionViandaService donacionViandaService,
            TarjetaService tarjetaService,
            HeladeraService heladeraService, ColaboradorRepository colaboradorRepository) {

        this.donacionDineroService = donacionDineroService;
        this.distribucionViandaService = distribucionViandaService;
        this.donacionViandaService = donacionViandaService;
        this.tarjetaService = tarjetaService;
        this.heladeraService = heladeraService;
        this.colaboradorRepository = colaboradorRepository;
    }

    public Double getPoints(Long id) {

        Double pesosDonados = safeGetValue(() -> donacionDineroService.getPesosDonados(id));
        Double viandasDistribuidas = safeGetValue(() -> distribucionViandaService.getViandasDistribuidas(id));
        Double viandasDonadas = safeGetValue(() -> donacionViandaService.getViandasDonadas(id));
        Double tarjetasRepartidas = safeGetValue(() -> tarjetaService.getTarjetasRepartidas(id));
        Double heladerasActivas = safeGetValue(() -> heladeraService.getHeladerasActivas(id));
        Double sumMesesActivas = safeGetValue(() -> heladeraService.getSumMesesActivas(id));

        Double totalPoints = (pesosDonados * PESOS_DONADOS_COEF)
                + (viandasDistribuidas * VIANDAS_DISTRIBUIDAS_COEF)
                + (viandasDonadas * VIANDAS_DONADAS_COEF)
                + (tarjetasRepartidas * TARJETAS_REPARTIDAS_COEF)
                + (heladerasActivas * sumMesesActivas * HELADERAS_ACTIVAS_COEF);

        Double puntosGastados = colaboradorRepository.getPuntosGastados(id);

        return totalPoints - puntosGastados;
    }

    private Double safeGetValue(Supplier<Double> supplier) {
        try {
            Double value = supplier.get();
            return value != null ? value : 0.0;
        } catch (Exception e) {
            // Loggear el error si es necesario
            return 0.0;
        }
    }
}