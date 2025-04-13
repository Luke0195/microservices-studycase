package br.com.lucassantos.orderservice.app.dtos.response;

import br.com.lucassantos.orderservice.app.entity.Order;


import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(
     UUID id,
     String name,
     Integer quantity,
     BigDecimal price,
     Order order) {
}
