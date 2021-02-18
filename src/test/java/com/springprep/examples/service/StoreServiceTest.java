package com.springprep.examples.service;

import com.springprep.examples.entity.Pen;
import com.springprep.examples.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @MockBean
    private StoreRepository storeRepository;

    @Test
    void addPenWorks() {

        Pen input = new Pen();
        input.setColor("Red");
        input.setPrice(12);


        Pen expectedOutput = new Pen();
        expectedOutput.setId(1);
        expectedOutput.setColor("Red");
        expectedOutput.setPrice(12);

        when(storeRepository.save(input)).thenReturn(expectedOutput);

        Pen actual = storeService.addPen(input);

        assertEquals(expectedOutput, actual);


    }

    @Test
    void getPenWorks() {


        Pen expectedOutput = new Pen();
        expectedOutput.setId(1);
        expectedOutput.setColor("Red");
        expectedOutput.setPrice(12);

        int penId = 1;


        when(storeRepository.findById(penId)).thenReturn(
                Optional.of(expectedOutput));

        Pen actual = storeService.getPen(penId);

        assertEquals(expectedOutput, actual);


    }

    @Test
    void getAllPenWorks() {


        List<Pen> pens = new ArrayList<>();
        pens.add(new Pen(1, "Red", 12));
        pens.add(new Pen(2, "Blue", 12));


        when(storeRepository.findAll()).thenReturn(pens);

        List<Pen> actualPens = storeService.getPens();

        assertEquals(pens, actualPens);


    }

    @Test
    void updatePenWorks() {

        Pen input = new Pen();
        input.setColor("Red");
        input.setPrice(12);


        Pen expectedOutput = new Pen();
        expectedOutput.setId(1);
        expectedOutput.setColor("Blue");
        expectedOutput.setPrice(12);

        when(storeRepository.existsById(1)).thenReturn(true);
        when(storeRepository.save(input)).thenReturn(expectedOutput);


        Pen actual = storeService.updatePen(1, input);

        assertEquals(expectedOutput, actual);


    }

    @Test
    void deletePenWorks() {

        Pen input = new Pen();
        input.setColor("Red");
        input.setPrice(12);


        Pen expectedOutput = new Pen();
        expectedOutput.setId(1);
        expectedOutput.setColor("Blue");
        expectedOutput.setPrice(12);

        when(storeRepository.existsById(1)).thenReturn(true);

        boolean actual = storeService.deletePen(1);

        assertTrue(actual);


    }

}