package com.springprep.examples.repository;

import com.springprep.examples.entity.House;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseAttributeRepository extends CrudRepository<House,Integer> {

    @Query(value = "Select * from House", nativeQuery = true)
    List<Object[]> getHouseAttributes(List<Long> houseIds);
}
