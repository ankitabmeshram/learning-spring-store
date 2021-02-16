package com.springprep.examples.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flower {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private String color;
    private int price;

    public Flower(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }
}
