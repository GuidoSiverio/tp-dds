package frba.utn.edu.ar.tp_dds.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfiguracionRabbitMQ {

    @Bean
    public Queue temperaturasQueue() {
        return new Queue("temperaturas", false);
    }

    @Bean
    public Queue alertasQueue() {
        return new Queue("alertas", false);
    }

    @Bean
    public Queue autorizacionesQueue() {
        return new Queue("autorizaciones", false);
    }
}

