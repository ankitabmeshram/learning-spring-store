package com.springprep.examples.service;

import com.springprep.examples.entity.Flower;
import com.springprep.examples.exception.DataNotFoundException;
import com.springprep.examples.repository.FlowerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlowerServiceTest {

    @Autowired
    private FlowerService flowerService;

    @MockBean
    private FlowerRepository flowerRepository;

    @Test
    void getFlowerWorks() {

        int flowerId = 1;
        Flower flower = new Flower(flowerId, "Rose", "Red", 12);

        when(flowerRepository.findById(flowerId)).thenReturn(
                Optional.of(flower));

        Flower flowerSaved = flowerService.getFlower(flowerId);

        Assertions.assertEquals(flowerId, flowerSaved.getId());
        Assertions.assertEquals(flower.getColor(), flowerSaved.getColor());
        Assertions.assertEquals(flower.getName(), flowerSaved.getName());
        Assertions.assertEquals(flower.getPrice(), flowerSaved.getPrice());
        verify(flowerRepository).findById(anyInt());
    }

    @Test
    void getFlowerThrowsException() {

        int flowerId = 1;
        Flower flower = new Flower(flowerId, "Rose", "Red", 12);

        when(flowerRepository.findById(flowerId)).thenReturn(Optional.empty());


        assertThrows(DataNotFoundException.class,
                () -> flowerService.getFlower(flowerId));

        verify(flowerRepository).findById(anyInt());

    }

    @Test
    void getFlowersReturnsEmptyist() {


        when(flowerRepository.findAll()).thenReturn(
                Collections.emptyList());

        List<Flower> flowers = flowerService.getFlowers();

        assertEquals(Collections.EMPTY_LIST, flowers);

        verify(flowerRepository).findAll();
    }

    @Test
    void getFlowersWorks() {

        int flowerId = 1;
        Flower flower = new Flower(flowerId, "Rose", "Red", 12);
        List<Flower> flowersExpected = new ArrayList<>();
        flowersExpected.add(flower);

        when(flowerRepository.findAll()).thenReturn(
                flowersExpected);

        List<Flower> flowersActual = flowerService.getFlowers();

        Assertions.assertEquals(flowersExpected, flowersActual);

        verify(flowerRepository).findAll();
    }


    @Test
    void getFlowerByNameWorks() {

        String flowerName = "Rose";
        Flower flower = new Flower(1, flowerName, "Red", 12);
        List<Flower> flowersExpected = new ArrayList<>();
        flowersExpected.add(flower);
        when(flowerRepository.findByName(flowerName)).thenReturn(
                flowersExpected);

        List<Flower> flowersActual = flowerService.getFlowerByName(flowerName);

        Assertions.assertEquals(flowersExpected, flowersActual);
        verify(flowerRepository).findByName(anyString());
    }

    @Test
    void getFlowerByNameThrowsException() {

        String flowerName = "Rose";

        when(flowerRepository.findByName(flowerName)).thenReturn(
                Collections.emptyList());

        List<Flower> flowersActual = flowerService.getFlowerByName(flowerName);

        Assertions.assertEquals(Collections.EMPTY_LIST, flowersActual);
        verify(flowerRepository).findByName(anyString());

    }

    @Test
    void addWorks() {
        Flower flowerInput = new Flower("Rose", "Red", 12);

        Flower flowerExpected = new Flower(1, "Rose", "Red", 12);

        when(flowerRepository.save(any(Flower.class))).thenReturn(
                flowerExpected);

        Flower flowerActual = flowerService.addFlower(flowerInput);

        assertNotEquals(0, flowerActual.getId());
        assertEquals(flowerExpected.getName(), flowerActual.getName());
    }

    @Test
    void deleteWorks() {

        int flowerId = 1;
        when(flowerRepository.findById(flowerId)).thenReturn(Optional.of(new Flower("Rose", "Red", 12)));

        boolean deleted = flowerService.delete(flowerId);

        verify(flowerRepository)
                .deleteById(flowerId);
        assertEquals(true,deleted);
    }

    @Test
    void deleteWhenRecordNotPresent() {

        int flowerId = 1;
        when(flowerRepository.findById(flowerId)).thenReturn(Optional.empty());

        boolean deleted = flowerService.delete(flowerId);


        assertEquals(false,deleted);
    }
    @Test
    void updateWorks() {
        Flower flowerInput = new Flower(1,"Rose2", "Red2", 12);

        Flower flowerExpected = new Flower(1, "Rose2", "Red2", 12);

        when(flowerRepository.save(flowerInput)).thenReturn(
                flowerExpected);

        Flower flowerActual = flowerService.update(flowerInput);

        assertEquals(flowerExpected.getId(), flowerActual.getId());
        assertEquals(flowerExpected.getName(), flowerActual.getName());
    }

}