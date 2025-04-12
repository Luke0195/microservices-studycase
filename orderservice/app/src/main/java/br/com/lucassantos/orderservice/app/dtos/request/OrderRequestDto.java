package br.com.lucassantos.orderservice.app.dtos.request;

import br.com.lucassantos.orderservice.app.entity.Product;
import br.com.lucassantos.orderservice.app.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderRequestDto(
        @NotEmpty(message = "The field client_name must be required")
        @JsonProperty("client_name")
        String clientName,
        @NotNull(message = "The field products must be required")
        List<Product> products) {
    @Override
    public String clientName() {
        return clientName;
    }


    @Override
    public List<Product> products() {
        return products;
    }
}
