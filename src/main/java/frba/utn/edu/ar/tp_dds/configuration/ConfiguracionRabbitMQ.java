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
    public Queue temperaturaQueue() {
        return new Queue("alerta.temperatura", false);
    }

    @Bean
    public Queue fraudeQueue() {
        return new Queue("alerta.fraude", false);
    }

    @Bean
    public Queue fallaConexionQueue() {
        return new Queue("alerta.conexion", false);
    }

    @Bean
    public Queue autorizacionesQueue() {
        return new Queue("autorizaciones", false);
    }
}

