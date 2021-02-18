package com.springprep.examples.repository;

import com.springprep.examples.entity.Pen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Pen, Integer> {
}
