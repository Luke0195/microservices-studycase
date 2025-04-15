package br.com.kitchenservice.app.listener;


import br.com.kitchenservice.app.dtos.OrderDto;
import br.com.kitchenservice.app.validator.OrderValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OrderListener {

    private final OrderValidator orderValidator;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "order.create")
    public void getOrder(String message) {
        try{
            OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
            orderValidator.validate(orderDto);
            System.out.println("Recendo a mensagem");
            System.out.println(message);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
