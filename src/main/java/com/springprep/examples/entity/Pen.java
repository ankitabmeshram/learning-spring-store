package com.springprep.examples.entity;

import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Pen {
    private int id;
    private String color;
    private int price;
}
