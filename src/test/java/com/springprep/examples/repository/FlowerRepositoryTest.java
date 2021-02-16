package com.springprep.examples.repository;

import com.springprep.examples.entity.Flower;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlowerRepositoryTest {

    @Autowired
    private FlowerRepository flowerRepository;


    @Test
    void findByNameworks() {
        Flower flower = new Flower("Rose", "Red", 12);
        flowerRepository.save(flower);
        String flowerName = "Rose";


        List<Flower> flowers = flowerRepository.findByName(flowerName);


        assertTrue(flowers.stream()
                          .allMatch(flwr -> flwr.getName() == flowerName));
    }

    @Test
    void updateWorks() {
        Flower savedFlower = flowerRepository.save(
                new Flower("Rose", "Red", 12));
        Flower flowerToUpdate = new Flower(savedFlower.getId(), "Rose2", "Red2",
                12);


        Flower updatedFlower = flowerRepository.save(flowerToUpdate);

        assertEquals(savedFlower.getId(), updatedFlower.getId());
        assertEquals("Rose2", updatedFlower.getName());
    }
}