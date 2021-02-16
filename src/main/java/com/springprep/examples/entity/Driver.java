package com.springprep.examples.entity;

import lombok.*;

@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Driver {
    private int id;
    private String name;
    private int age;
}
