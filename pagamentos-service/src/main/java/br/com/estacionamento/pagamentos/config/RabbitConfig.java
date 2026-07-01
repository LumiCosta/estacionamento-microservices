package br.com.estacionamento.pagamentos.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;
@Configuration public class RabbitConfig{public static final String EXCHANGE="parking.events";
    @Bean DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);}
    @Bean Queue pagamentoQueue(){
        return QueueBuilder.durable("pagamentos.processados").deadLetterExchange(EXCHANGE).deadLetterRoutingKey("pagamento.dlq").build();}
    @Bean Queue pagamentoDlq(){
        return QueueBuilder.durable("pagamentos.dlq").build();}
    @Bean Binding b1(){
        return BindingBuilder.bind(pagamentoQueue()).to(exchange()).with("pagamento.processado");}
    @Bean Binding b2(){
        return BindingBuilder.bind(pagamentoDlq()).to(exchange()).with("pagamento.dlq");}
}
