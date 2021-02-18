package com.springprep.examples.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode

public class Pen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String color;
    private int price;


}
