package com.springprep.examples.service;

import com.springprep.examples.entity.City;
import com.springprep.examples.entity.House;
import com.springprep.examples.repository.HouseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private HouseRespository houseRespository;

    @Autowired
    public HouseService(HouseRespository houseRespository) {
        this.houseRespository = houseRespository;
    }

    public List<City> getCities() {

        List<House> houses = houseRespository.getListOfHouse();

        List<Long> zipCodes = houses.stream()
                                    .map(house ->
                                        house.getZipCode()
                                             .longValue()
                                    )
                                    .collect(
                                            Collectors.toList());

        return houseRespository.getListOfCities(zipCodes);
    }
}
