package br.com.lucassantos.orderservice.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FieldErrorResponseDto(
        @JsonProperty("field_name")
        String fieldName,
        String description) {
}
