package com.springprep.examples.repository;

import com.springprep.examples.entity.City;
import com.springprep.examples.entity.House;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRespository extends CrudRepository<House,Integer> {
    @Query(value = "Select * from House", nativeQuery = true)
    List<House> getListOfHouse();

    @Query(value = "Select * from House", nativeQuery = true)
    List<City> getListOfCities(List<Long> anyList);
}
