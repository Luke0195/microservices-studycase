package br.com.lucassantos.orderservice.app.service;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import br.com.lucassantos.orderservice.app.dtos.response.OrderResponseDto;
import br.com.lucassantos.orderservice.app.entity.Order;
import br.com.lucassantos.orderservice.app.entity.Product;
import br.com.lucassantos.orderservice.app.entity.enums.OrderStatus;
import br.com.lucassantos.orderservice.app.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitService rabbitService;

    @Transactional
    public OrderResponseDto add(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setClientName(orderRequestDto.clientName());
        order.setHourTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<Product> products = new ArrayList<>();
        for(Product product:orderRequestDto.products()){
            products.add(Product.builder().price(product.getPrice()).order(order).price(product.getPrice()).name(product.getName()).quantity(product.getQuantity()).build());
        }
        order.setProducts(products);
        order.setEnviado(true);
        order = orderRepository.save(order);
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(), order.getClientName(), order.getHourTime(), order.getStatus(), order.getProducts());
        rabbitService.notitfyRabbitMq(orderRequestDto);
        return orderResponseDto;
    };
}
