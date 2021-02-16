package com.springprep.examples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@AllArgsConstructor
@EqualsAndHashCode
public class City {
    private String name;


}
