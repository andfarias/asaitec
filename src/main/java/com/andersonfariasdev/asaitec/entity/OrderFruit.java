package com.andersonfariasdev.asaitec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class OrderFruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderFruitPK primaryKey = new OrderFruitPK();

    private Integer quantity;
    private Double price;

    public OrderFruit(Order order, Fruit fruit, Integer quantity) {
        super();
        primaryKey.setOrder(order);
        primaryKey.setFruit(fruit);
        this.quantity = quantity;
        this.price = calculatePrice();
    }

    private Double calculatePrice() {
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFruit that = (OrderFruit) o;
        return Objects.equals(primaryKey, that.primaryKey) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey, quantity, price);
    }
}
