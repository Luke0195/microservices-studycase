package br.com.lucassantos.orderservice.app.controller.exceptions;

import br.com.lucassantos.orderservice.app.dtos.response.FieldErrorResponseDto;
import br.com.lucassantos.orderservice.app.dtos.response.StandardErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class OrderServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDto> handleValidationException(
            HttpServletRequest httpServletRequest, MethodArgumentNotValidException methodArgumentNotValidException){
        LocalDateTime now = LocalDateTime.now();
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        String error = "Hibernate Validation Exception";
        Set<FieldErrorResponseDto> errors = getFieldErrors(methodArgumentNotValidException);
        StandardErrorResponseDto responseDto = new StandardErrorResponseDto(LocalDateTime.now(), statusCode, error,
                "Please validate th errors to validate the payload", httpServletRequest.getRequestURI(),errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);

    };

    public static Set<FieldErrorResponseDto> getFieldErrors(MethodArgumentNotValidException methodArgumentNotValidException){
        Set<FieldErrorResponseDto> errors = new HashSet<>();
        methodArgumentNotValidException.getFieldErrors().forEach(x -> {
            String fieldName = x.getField();
            String fieldDescription = x.getDefaultMessage();
            errors.add(new FieldErrorResponseDto(fieldName, fieldDescription));
        });
        return errors;
    }
}
