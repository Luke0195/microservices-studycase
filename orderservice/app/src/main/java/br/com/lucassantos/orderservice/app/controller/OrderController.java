package br.com.lucassantos.orderservice.app.controller;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import br.com.lucassantos.orderservice.app.dtos.response.OrderResponseDto;
import br.com.lucassantos.orderservice.app.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService service;


    @PostMapping(value = "/order")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = service.add(orderRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand("/{id}", orderResponseDto.id()).toUri();
        return ResponseEntity.created(uri).body(orderResponseDto);
    }
}
