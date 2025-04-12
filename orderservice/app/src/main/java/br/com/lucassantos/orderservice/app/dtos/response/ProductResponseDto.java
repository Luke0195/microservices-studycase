package br.com.lucassantos.orderservice.app.dtos.response;

import br.com.lucassantos.orderservice.app.entity.Order;


import java.math.BigDecimal;

public record ProductResponseDto(
     Long id,
     String name,
     Integer quantity,
     BigDecimal price,
     Order order) {
}
