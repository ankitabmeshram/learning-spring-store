package com.springprep.examples.repository;

import com.springprep.examples.entity.City;
import com.springprep.examples.entity.House;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseRespository extends CrudRepository<House,Integer> {
    @Query("Select * from House")
    List<House> getListOfHouse();

    @Query("Select * from House")
    List<City> getListOfCities(List<Long> anyList);
}
