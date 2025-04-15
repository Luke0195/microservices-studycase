package br.com.kitchenservice.app.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {


    @RabbitListener(queues = "order.create")
    public void getOrder(String message){
        System.out.println("Recendo a mensagem");
        System.out.println(message);
    }
}
