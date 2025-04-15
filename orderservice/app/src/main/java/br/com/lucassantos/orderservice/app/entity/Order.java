package br.com.lucassantos.orderservice.app.entity;

import br.com.lucassantos.orderservice.app.dtos.request.OrderRequestDto;
import br.com.lucassantos.orderservice.app.entity.enums.OrderStatus;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "client_name")
    private String clientName;
    @Column(name="hour_time")
    private LocalDateTime hourTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
    private Boolean enviado = Boolean.FALSE;


    public static Order makeOrder(OrderRequestDto orderRequestDto){
        Order order = new  Order();
        order.setClientName(orderRequestDto.clientName());
        order.setProducts(orderRequestDto.products());
        return order;
    }


}
