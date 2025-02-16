package frba.utn.edu.ar.tp_dds.services;

import frba.utn.edu.ar.tp_dds.entities.incidente.Alerta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RabbitListenerService {

    private final HeladeraService heladeraService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListenerService.class);

    public RabbitListenerService(HeladeraService heladeraService) {
        this.heladeraService = heladeraService;
    }

    @RabbitListener(queues = "alerta.temperatura")
    @Transactional
    public void recibirAlertasPorTemperatura(String mensaje) {
        String[] parts = mensaje.split("\\|");
        String alertMessage = parts[0];
        Long heladeraId = Long.parseLong(parts[1]);
        LOGGER.info("❌ Alerta recibida: {}", alertMessage);
        Alerta alerta = new Alerta("Temperatura");
        heladeraService.notificarIncidente(heladeraId, alerta, "❌ Alerta de temperatura recibida: " + alertMessage);
    }

    @RabbitListener(queues = "alerta.fraude")
    @Transactional
    public void recibirFraude(String mensaje) {
        String[] parts = mensaje.split("\\|");
        String alertMessage = parts[0];
        Long heladeraId = Long.parseLong(parts[1]);
        LOGGER.info("❌ Alerta de fraude recibida: {}", mensaje);
        Alerta alerta = new Alerta("Fraude");
        heladeraService.notificarIncidente(heladeraId, alerta, "❌ Alerta de fraude recibida: " + alertMessage);

    }

    @RabbitListener(queues = "alerta.conexion")
    @Transactional
    public void recibirFallaConexion(String mensaje) {
        String[] parts = mensaje.split("\\|");
        String alertMessage = parts[0];
        Long heladeraId = Long.parseLong(parts[1]);
        LOGGER.info("❌ Alerta por falla de conexion recibida: {}", mensaje);
        Alerta alerta = new Alerta("Falla en la conexion");
        heladeraService.notificarIncidente(heladeraId, alerta, "❌ Alerta por falla de conexion recibida: " + alertMessage);

    }


}
