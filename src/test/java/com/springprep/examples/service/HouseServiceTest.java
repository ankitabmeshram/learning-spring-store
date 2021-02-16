package com.springprep.examples.service;

import com.springprep.examples.entity.City;
import com.springprep.examples.entity.House;
import com.springprep.examples.repository.HouseRespository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class HouseServiceTest {

    @MockBean
    private HouseRespository houseRespository;


    @Autowired
    private HouseService houseService;

    @Test
    void demo() {
        House apartment=new House(1,new BigInteger("48326"));
        House rowHouse=new House(1,new BigInteger("48375"));

        List<House> houses = Arrays.asList(apartment, rowHouse);
        when(houseRespository.getListOfHouse()).thenReturn(
                houses);
        List<City> expectedCities = Arrays.asList(new City("Jersy"),
                new City("Roanoke"));
        when(houseRespository.getListOfCities(anyList())).thenReturn(
                expectedCities);


        List<City> actualCities=houseService.getCities();

        assertNotNull(actualCities);
        assertEquals(expectedCities.size(),actualCities.size());
        assertTrue(actualCities.containsAll(expectedCities));

        ArgumentCaptor<List> captor=ArgumentCaptor.forClass(List.class);

        verify(houseRespository).getListOfCities(captor.capture());
        verify(houseRespository).getListOfHouse();

        List<Long> zipCodes = captor.getValue();
        assertTrue(zipCodes.containsAll(Arrays.asList(48326L,48375L)));


    }
}