package br.com.lucassantos.orderservice.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import br.com.lucassantos.orderservice.app.entity.Order;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="tb_products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

}
