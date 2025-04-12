package br.com.lucassantos.orderservice.app.dtos.request;

import br.com.lucassantos.orderservice.app.entity.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;

public record ProductRequestDto(
        @NotEmpty(message = "The field name must be required")
        String name,
        @NotNull(message = "The field quantity must be required")
        Integer quantity,
        @NotNull(message = "The field price must be required")
        BigDecimal price,
        @NotNull(message = "The field order must be required")
        Order order) {

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer quantity() {
        return quantity;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public Order order() {
        return order;
    }
}
