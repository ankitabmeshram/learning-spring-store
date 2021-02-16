package com.springprep.examples.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HouseData {
    // data 0
    Long houseId;
    // data 1
    String name;
    // data 2
    String city;

    public HouseData(Long houseId, String name, String city) {
        this.houseId = houseId;
        this.name = name;
        this.city = city;
    }

    // attr-id 1
    String cost;

    // attr-id 5
    String lengthAttr;
    String widthAttr;
    String heightAttr;

    // attr-id 23
    Integer bedrooms;
}
