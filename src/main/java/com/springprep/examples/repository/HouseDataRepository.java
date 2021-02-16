package com.springprep.examples.repository;

import com.springprep.examples.entity.House;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseDataRepository extends CrudRepository<House,Integer> {

    List<Object[]> getHouseDataList();
}
