package br.com.lucassantos.orderservice.app.config;

import br.com.lucassantos.orderservice.app.properties.QueueProperties;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
    public FanoutExchange createFanoutExchangeOrderCreate(){
        return ExchangeBuilder.fanoutExchange("order.create.exchange").build();
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(createFanoutExchangeOrderCreate());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    };

}
