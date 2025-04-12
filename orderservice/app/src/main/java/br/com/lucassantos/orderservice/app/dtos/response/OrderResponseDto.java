package br.com.lucassantos.orderservice.app.dtos.response;

import br.com.lucassantos.orderservice.app.entity.Product;
import br.com.lucassantos.orderservice.app.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        @JsonProperty("client_name")
        String clientName,
        @JsonProperty("hour_time")
        LocalDateTime hourTime,
        @JsonProperty("order_status")
        OrderStatus orderStatus,
        List<Product> products
        ) {
    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String clientName() {
        return clientName;
    }

    @Override
    public LocalDateTime hourTime() {
        return hourTime;
    }

    @Override
    public OrderStatus orderStatus() {
        return orderStatus;
    }

    @Override
    public List<Product> products() {
        return products;
    }
}
