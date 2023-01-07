package com.andersonfariasdev.asaitec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fruit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.fruit")
    private List<OrderFruit> itens = new ArrayList<>();

    @Override
    public String toString() {
        return id + ", " + name + ", " + price;
    }
}
