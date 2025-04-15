package br.com.kitchenservice.app.dtos;

import br.com.kitchenservice.app.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record OrderDto(
        @NotNull(message = "The field id must be required")  UUID id,
        @NotEmpty(message = "The field client_name must be required")  @JsonProperty("client_name") String clientName,
        @NotNull(message = "The field order_status must be required ") @JsonProperty("order_status") OrderStatus orderStatus,
        @NotNull(message = "The field hour_time must be required")  @JsonProperty("hour_time") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime hourTime,
        @NotNull(message = "The field products must be required") List<ProductDto> products) {

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
    public List<ProductDto> products() {
        return products;
    }
}
