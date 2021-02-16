package com.springprep.examples.repository;

import com.springprep.examples.entity.Flower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends CrudRepository<Flower,Integer> {
    List<Flower> findByName(String flowerName);
}
