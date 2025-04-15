package br.com.lucassantos.orderservice.app.service;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitService {

    private RabbitTemplate rabbitTemplate;

    public void notitfyRabbitMq(OrderRequestDto orderRequestDto){
        rabbitTemplate.convertAndSend("order.create.exchange", "", orderRequestDto);
    }
}
