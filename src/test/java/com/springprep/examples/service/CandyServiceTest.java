package com.springprep.examples.service;

import com.springprep.examples.service.CandyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CandyServiceTest {

    @Autowired
    private CandyService candyService;

    @Test
    void ReturnSizeOfInputList_When_AllElementsOfListIsSame() {

        int value=2;
        List<Integer> children = new ArrayList<Integer>();
        children.add(value);
        children.add(value);
        children.add(value);
        children.add(value);
        children.add(value);


        Integer candiesCount = candyService.getCandies(children);

        Assertions.assertEquals(children.size(), candiesCount);
    }


    @Test
    void ReturnValidCount_When_InputListIsValid() {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(2);
        children.add(2);


        Integer candiesCount = candyService.getCandies(children);

        Assertions.assertEquals(4, candiesCount);
    }

    @Test
    void ReturnValidCount_When_InputListIsValid1() {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(3);
        children.add(2);
        children.add(2);
        children.add(1);


        Integer candiesCount = candyService.getCandies(children);

        Assertions.assertEquals(7, candiesCount);
    }
    @Test
    void ReturnValidCount_When_InputListIsValid2() {

        List<Integer> children = new ArrayList<Integer>();
        children.add(1);
        children.add(3);
        children.add(4);
        children.add(5);
        children.add(2);


        Integer candiesCount = candyService.getCandies(children);

        Assertions.assertEquals(11, candiesCount);
    }

}