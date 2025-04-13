package br.com.lucassantos.orderservice.app.service;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import br.com.lucassantos.orderservice.app.dtos.response.OrderResponseDto;
import br.com.lucassantos.orderservice.app.entity.Order;
import br.com.lucassantos.orderservice.app.entity.Product;
import br.com.lucassantos.orderservice.app.entity.enums.OrderStatus;
import br.com.lucassantos.orderservice.app.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RabbitService rabbitService;

    @Test
    @DisplayName(" add should return and OrderResponseDto when valid data is provided")
    void addShouldReturnsOrderResponseDtoWhenValidDataIsProvided(){
        OrderRequestDto orderRequestDto = new OrderRequestDto("any_name", List.of(Product.builder()
                        .price(BigDecimal.valueOf(40.50))
                        .quantity(3)
                        .name("any_name")
                .build()));

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(Order.builder()
                        .id(UUID.randomUUID())
                        .clientName("any_name")
                        .products(orderRequestDto.products())
                        .hourTime(LocalDateTime.now())
                        .status(OrderStatus.PENDING)
                        .enviado(true)
                .build());
        Mockito.doNothing().when(rabbitService).notitfyRabbitMq(Mockito.any());
        OrderResponseDto orderResponseDto = orderService.add(orderRequestDto);
        Assertions.assertNotNull(orderResponseDto.id());
        Assertions.assertNotNull(orderResponseDto.clientName());
        Assertions.assertEquals(1, orderResponseDto.products().size());
    }
}