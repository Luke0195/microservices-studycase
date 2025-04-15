package br.com.kitchenservice.app.validator;

import br.com.kitchenservice.app.dtos.OrderDto;
import br.com.kitchenservice.app.exceptions.InvalidParamException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {

    private final Validator validator;

    public OrderValidator(Validator validator){
        this.validator = validator;
    }



    public void validate(OrderDto orderDto){
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
