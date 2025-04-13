package br.com.lucassantos.orderservice.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Set;

public record StandardErrorResponseDto(
        LocalDateTime timestamp,
        @JsonProperty("status_code")
        Integer statusCode,
        String error,
        @JsonProperty("exception_message")
        String exceptionMessage,
        @JsonProperty("path_url")
        String pathUrl,
        @JsonProperty("field_errors")
        Set<FieldErrorResponseDto> fieldErrors) {
}
