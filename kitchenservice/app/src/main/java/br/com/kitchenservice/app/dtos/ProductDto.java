package br.com.kitchenservice.app.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;


public record ProductDto(
        @NotNull(message = "The field id must be required") UUID id,
        @NotEmpty(message = "The field name must be required") String name,
        @NotNull(message = "The field quantity must be required") @Min(value = 1, message = "The field quantity must be a least on item")
        Integer quantity,
        BigDecimal price) {

    @Override
    public UUID id() {
        return id;
    }

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
}
