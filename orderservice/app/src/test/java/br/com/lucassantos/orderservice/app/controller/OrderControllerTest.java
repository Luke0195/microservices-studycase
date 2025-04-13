package br.com.lucassantos.orderservice.app.controller;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import br.com.lucassantos.orderservice.app.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("POST- Should returns 400 if no clienteName is provided")
    @Test
    void postShouldReturnsBadRequestIfNoClientNameIsProvided() throws Exception{
        Product product = new Product(UUID.randomUUID(), "any_name", 5, BigDecimal.valueOf(40.50), null);
        OrderRequestDto orderRequestDto = new OrderRequestDto(null, List.of(product));
        String jsonBody = objectMapper.writeValueAsString(orderRequestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody));
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("POST - Should returns 400 if no products is provided")
    @Test
    void postShouldReturnsBadRequestIfNoProductIsProvided() throws Exception{
        OrderRequestDto orderRequestDto = new OrderRequestDto("any_name", null);
        String jsonBody = objectMapper.writeValueAsString(orderRequestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("POST - Should returns 201 when valid data is provided")
    @Test
    void postShouldReturnsCreatedWhenValidDataIsProvided() throws Exception{
        Product product = Product.builder().id(UUID.randomUUID()).price(BigDecimal.valueOf(40.50)).name("any_product").quantity(30).build();
        OrderRequestDto orderRequestDto = new OrderRequestDto("any_name", List.of(product));
        String jsonBody = objectMapper.writeValueAsString(orderRequestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}