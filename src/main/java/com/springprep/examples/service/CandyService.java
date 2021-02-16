package com.springprep.examples.service;

import com.springprep.examples.entity.Flower;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.max;


@Service
public class CandyService {
    public Integer getCandies(List<Integer> children) {


        int size = children.size();
        if (children.stream()
                    .distinct()
                    .count() == 1) {
            return size;
        }


        List<Integer> candiesForEachChild = new ArrayList<Integer>(
                Collections.nCopies(size, 1));


        for (int i = 1; i < size; i++) {
            if (children.get(i) > children.get(i - 1)) {
                candiesForEachChild.set(i, candiesForEachChild.get(i - 1) + 1);
            }
        }

        for (int i = size - 2; i >= 0; i--) {
            if (children.get(i) > children.get(i + 1)) {
                candiesForEachChild.set(i, max(candiesForEachChild.get(i),
                        candiesForEachChild.get(i + 1) + 1));
            }
        }

        return candiesForEachChild.stream()
                                  .mapToInt(Integer::intValue)
                                  .sum();

    }


    public Flower addFlower(Flower flower) {
        return null;
    }
}
