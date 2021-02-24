package com.springprep.examples.repository;

import com.springprep.examples.entity.Pen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void SaveWorks() {
        Pen input = new Pen();
        input.setColor("Red");
        input.setPrice(12);

        Pen actual = storeRepository.save(input);

        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
        assertEquals(input.getColor(), actual.getColor());
        assertEquals(input.getPrice(), actual.getPrice());
    }


    @Test
    void getWorks() {
        Pen input = new Pen();
        input.setColor("Red");
        input.setPrice(12);
        Pen pen = storeRepository.save(input);

        Optional<Pen> actualPen = storeRepository.findById(pen.getId());


        assertTrue(actualPen.isPresent());

        assertNotNull(actualPen);
        assertTrue(actualPen.get()
                            .getId() > 0);
        assertEquals(pen.getColor(), actualPen.get()
                                              .getColor());
        assertEquals(pen.getPrice(), actualPen.get()
                                              .getPrice());
    }

    @Test
    void getworks2() {


    }


}