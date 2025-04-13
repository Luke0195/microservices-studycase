package br.com.lucassantos.orderservice.app.config;

import br.com.lucassantos.orderservice.app.properties.QueueProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final QueueProperties queueProperties;

    public RabbitMQConfig(QueueProperties queueProperties){
        this.queueProperties = queueProperties;
    }

    @Bean
    public Queue queue(){
        System.out.println("✅ Criando fila com nome: order.create");
        return new Queue("order.create", true);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    };

}
