package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.heladera.Heladera;
import frba.utn.edu.ar.tp_dds.repositories.HeladeraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RabbitProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final HeladeraRepository heladeraRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducerService.class);
    private List<Long> heladerasConFallaDeConexion = new ArrayList<>();
    private List<Long> heladerasConFraude = new ArrayList<>();

    public RabbitProducerService(RabbitTemplate rabbitTemplate, HeladeraRepository heladeraRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.heladeraRepository = heladeraRepository;
    }

    public void send(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
    }

    public void send(String queue, String message, Long id) {
        rabbitTemplate.convertAndSend(queue, message + "|" + id.toString());
    }

    //@Scheduled(cron = "0 */5 * * * *")
    //@Scheduled(fixedRate = 300000)
    @Scheduled(fixedRate = 7000)
    public void calcularTemperatura() {
        Random random = new Random();
        int delta = 5;

        heladeraRepository.findAll().stream()
            .filter(heladera -> heladera.isActiva() && !heladerasConFallaDeConexion.contains(heladera.getId()))
            .forEach(heladera -> {

                double minExtremo = heladera.getTempMinAceptable() - delta;
                double maxExtremo = heladera.getTempMaxAceptable() + delta;
                double temperatura = Math.round(random.nextDouble((maxExtremo - minExtremo) + minExtremo) * 100.0) / 100.0;
                heladera.setUltimaTemp(temperatura);
                heladeraRepository.save(heladera);
                LOGGER.info("🌡 Temperatura de la heladera {}: {}°C", heladera.getNombre(), temperatura);
                if (temperatura < heladera.getTempMinAceptable()) {
                    send("alerta.temperatura", "❄️ La heladera " + heladera.getNombre() + " está por debajo de la temperatura mínima aceptable.", heladera.getId());
                } else if (temperatura > heladera.getTempMaxAceptable()) {
                    send("alerta.temperatura", "🔥 La heladera " + heladera.getNombre() + " está por encima de la temperatura máxima aceptable.", heladera.getId());
                }

            });
    }

    //5 segundos
    @Scheduled(fixedRate = 6000)
    //@Scheduled(fixedRate = 350000)
    public void fraude(){
        Random random = new Random();
        heladeraRepository.findAll().stream()
            .filter( heladera -> !heladera.isActiva() && !heladerasConFraude.contains(heladera.getId()))
            .forEach(heladera -> {
                double probabilidad = random.nextDouble();
                LOGGER.info("Probabilidad de fraude: {}", probabilidad);
                if (probabilidad < 0.05) {
                    heladerasConFraude.add(heladera.getId());
                    send("alerta.fraude", "🚨 Se ha detectado un movimiento en la heladera cerrada " + heladera.getNombre(), heladera.getId());
                }
            });
    }

    //@Scheduled(fixedRate = 400000)
    @Scheduled(fixedRate = 5000)
    public void fallaConexion(){
        Random random = new Random();
        heladeraRepository.findAll().stream()
            .filter(Heladera::isActiva)
            .forEach(heladera -> {
                double probabilidad = random.nextDouble();
                LOGGER.info("Probabilidad de falla de conexión: {}", probabilidad);
                if (probabilidad < 0.05) {
                    heladerasConFallaDeConexion.add(heladera.getId());
                    send("alerta.conexion", "🚨 Se ha detectado una falla de conexión en la heladera, no se puede calcular la temperatura de " + heladera.getNombre(), heladera.getId());
                }
            });
    }

}
