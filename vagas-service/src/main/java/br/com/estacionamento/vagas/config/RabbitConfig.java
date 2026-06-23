package br.com.estacionamento.vagas.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;
@Configuration
public class RabbitConfig{
    public static final String EXCHANGE="parking.events";
    public static final String SENSOR_QUEUE="sensor.vagas";
    public static final String DLQ="sensor.vagas.dlq";
    @Bean DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);
    }
    @Bean Queue sensorQueue(){
        return QueueBuilder.durable(SENSOR_QUEUE).deadLetterExchange(EXCHANGE).deadLetterRoutingKey("sensor.dlq").build();
    }
    @Bean Queue dlq(){
        return QueueBuilder.durable(DLQ).build();
    }
    @Bean Binding b1(){
        return BindingBuilder.bind(sensorQueue()).to(exchange()).with("sensor.vaga");
    }
    @Bean Binding b2(){return BindingBuilder.bind(dlq()).to(exchange()).with("sensor.dlq");
    }
}
