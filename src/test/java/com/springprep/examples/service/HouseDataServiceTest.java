package com.springprep.examples.service;

import com.springprep.examples.entity.HouseData;
import com.springprep.examples.repository.HouseAttributeRepository;
import com.springprep.examples.repository.HouseDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class HouseDataServiceTest {

    @InjectMocks
    HouseDataService houseDataService;

    @Mock
    HouseDataRepository houseDataRepository;

    @Mock
    HouseAttributeRepository houseAttributeRepository;

    @Test
    void getCombinedData_should_returnHouseDataCorrectly() {
        when(houseDataRepository.getHouseDataList()).thenReturn(
                mockHouseDataList());
        when(houseAttributeRepository.getHouseAttributes(any())).thenReturn(
                mockHouseAttributeDataList());


        List<HouseData> actualHouseData = houseDataService.getCombinedData();

        Assertions.assertThat(actualHouseData)
                  .isNotNull();
        Assertions.assertThat(actualHouseData.size())
                  .isEqualTo(1);

        HouseData actualFirstHouseData = actualHouseData.get(0);

        // core data
        Assertions.assertThat(actualFirstHouseData.getHouseId())
                  .isEqualTo(1L);
        Assertions.assertThat(actualFirstHouseData.getName())
                  .isEqualTo("HouseName-01");
        Assertions.assertThat(actualFirstHouseData.getCity())
                  .isEqualTo("Auburn Hills");


        // combined data
        Assertions.assertThat(actualFirstHouseData.getCost())
                  .isEqualTo("543.34");

        Assertions.assertThat(actualFirstHouseData.getLengthAttr())
                  .isEqualTo("12");
        Assertions.assertThat(actualFirstHouseData.getWidthAttr())
                  .isEqualTo("55");
        Assertions.assertThat(actualFirstHouseData.getHeightAttr())
                  .isEqualTo("98");

        Assertions.assertThat(actualFirstHouseData.getBedrooms())
                  .isEqualTo("4");
    }


    List<Object[]> mockHouseDataList() {
        Object[] firstHouseData = new Object[5];

        firstHouseData[0] = new BigInteger("1");
        firstHouseData[1] = "HouseName-01";
        firstHouseData[2] = "Auburn Hills";

        List<Object[]> houseDataList = new ArrayList<>();
        houseDataList.add(firstHouseData);

        return houseDataList;
    }

    List<Object[]> mockHouseAttributeDataList() {
        Object[] costData = new Object[]{new BigInteger("1"), "1", "543.34"};
        Object[] dimensionData = new Object[]{new BigInteger(
                "1"), "5", "12 55 98"};
        Object[] bedroomsData = new Object[]{new BigInteger("1"), "23", "4"};

        List<Object[]> attributeData = new ArrayList<>();
        attributeData.add(costData);
        attributeData.add(dimensionData);
        attributeData.add(bedroomsData);

        return attributeData;
    }
}