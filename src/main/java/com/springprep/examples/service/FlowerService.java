package com.springprep.examples.service;

import com.springprep.examples.entity.Flower;
import com.springprep.examples.exception.DataNotFoundException;
import com.springprep.examples.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlowerService {

    private FlowerRepository flowerRepository;

    @Autowired
    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }


    public Flower getFlower(int id) {
        Optional<Flower> flower = flowerRepository.findById(id);
        if (flower.isPresent()) {
            return flower.get();
        }

        throw new DataNotFoundException(
                "Flower with id " + id + " is not available in Database");

    }

    public List<Flower> getFlowers() {
        List<Flower> flowers = new ArrayList<>();
        flowerRepository.findAll()
                        .forEach(flower -> flowers.add(flower));
        return flowers;
       /* return StreamSupport.stream(flowerRepository.findAll()
                                                    .spliterator(), false)
                            .collect(Collectors.toList());*/
    }

    public List<Flower> getFlowerByName(String flowerName) {
        return flowerRepository.findByName(flowerName);
    }

    public Flower addFlower(Flower flower) {
        return flowerRepository.save(flower);
    }

    public boolean delete(int flowerId) {
        if (flowerRepository.findById(flowerId)
                            .isPresent()) {
            flowerRepository.deleteById(flowerId);
            return true;
        }
        return false;
    }

    public Flower update(Flower flower) {
        return flowerRepository.save(flower);
    }
}
