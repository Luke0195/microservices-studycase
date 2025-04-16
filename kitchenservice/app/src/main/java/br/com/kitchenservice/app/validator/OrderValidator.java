package br.com.kitchenservice.app.validator;

import br.com.kitchenservice.app.dtos.OrderDto;
import br.com.kitchenservice.app.exceptions.InvalidParamException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {

    private final Validator validator;
    private final ObjectMapper objectMapper;

    public OrderValidator(Validator validator, ObjectMapper objectMapper){
        this.validator = validator;
        this.objectMapper = objectMapper;
    }



    public void validate(String message) throws JsonProcessingException {
        System.out.println(message);
        OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
        Set<ConstraintViolation<OrderDto>> errors = validator.validate(orderDto);
        if(!errors.isEmpty()){
            errors.forEach( error -> {
                throw new InvalidParamException(error.getMessage());
            });
            return;
        }
        System.out.println("Dados Validados");
    }
}
