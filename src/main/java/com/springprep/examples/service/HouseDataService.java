package com.springprep.examples.service;

import com.springprep.examples.entity.City;
import com.springprep.examples.entity.House;
import com.springprep.examples.entity.HouseData;
import com.springprep.examples.repository.HouseAttributeRepository;
import com.springprep.examples.repository.HouseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseDataService {

    private HouseDataRepository houseRespository;
    private HouseAttributeRepository houseAttributeRepository;

    @Autowired
    public HouseDataService(HouseDataRepository houseRespository, HouseAttributeRepository houseAttributeRepository) {
        this.houseRespository = houseRespository;
        this.houseAttributeRepository = houseAttributeRepository;
    }

    public List<HouseData> getCombinedData() {
        List<HouseData> combinedHouseDataList = getHouseData();


        List<Long> houseids = combinedHouseDataList.stream()
                                                   .map(house -> house.getHouseId())
                                                   .collect(
                                                           Collectors.toList());

        List<Object[]> houseAttributes = houseAttributeRepository.getHouseAttributes(
                houseids);

        mergeHouseData(houseAttributes, combinedHouseDataList);

        return combinedHouseDataList;
    }

    private void mergeHouseData(List<Object[]> houseAttributes, List<HouseData> combinedHouseDataList) {

        Map<Long, List<Object[]>> attributesMappedByHouseId = getMappedAttributes(
                houseAttributes);


        for (HouseData houseData : combinedHouseDataList) {

            Long houseId = houseData.getHouseId();

            List<Object[]> attributes = attributesMappedByHouseId.get(houseId);

            attributes.stream()
                      .forEach(attribute ->
                              {
                                  Long attrId = ((BigInteger) attribute[1]).longValue();
                                  String attrVal = attribute[2].toString();
                                  if (attrId == 1L) {
                                      houseData.setCost(attrVal);
                                  }
                                  if (attrId == 5L) {
                                      String[] dimensions = attrVal.split(" ", 3);

                                      houseData.setLengthAttr(dimensions[0]);
                                      houseData.setWidthAttr(dimensions[1]);
                                      houseData.setHeightAttr(dimensions[2]);
                                  }
                                  if (attrId == 23L) {
                                      houseData.setBedrooms(Integer.valueOf(attrVal));
                                  }
                              }
                      );
        }

    }

    private List<HouseData> getHouseData() {
        List<Object[]> houseDataList = houseRespository.getHouseDataList();

        List<HouseData> combinedHouseDataList = new ArrayList<>(
                houseDataList.size());

        houseDataList
                .forEach(
                        house ->
                        {
                            combinedHouseDataList.add(
                                    new HouseData(
                                            ((BigInteger) house[0]).longValue(),
                                            (String) house[1],
                                            (String) house[2]));
                        }
                );
        return combinedHouseDataList;
    }


    ////////////////////////////////////////////////////////////////////////


    public List<HouseData> getCombinedDataPrac() {

        List<Object[]> houses = getListOfHouses();

        List<Long> houseIds = getHousesIds(houses);

        List<Object[]> attributes = getAttributesForHouseIds(houseIds);

        return createHouseData(houses, attributes);


    }

    private List<HouseData> createHouseData(List<Object[]> houses, List<Object[]> attributes) {

        List<HouseData> initialHouseData = createInitialHouseData(houses);
        addAttributes(initialHouseData, attributes);
        return initialHouseData;

    }

    private void addAttributes(List<HouseData> initialHouseData, List<Object[]> attributes) {

        Map<Long, List<Object[]>> attributesMappedByHouseID = getMappedAttributes(
                attributes);
        for (HouseData houseData : initialHouseData) {

            List<Object[]> attributesForGivenId = attributesMappedByHouseID.get(
                    houseData.getHouseId());

            for (Object[] attr : attributesForGivenId) {

                if (((BigInteger) attr[0]).longValue() == 1L) {
                    houseData.setCost((String) attr[2]);
                }
                if (((BigInteger) attr[0]).longValue() == 5L) {
                    String[] dimensions = attr[2].toString()
                                                 .split(" ", 3);
                    houseData.setLengthAttr(dimensions[0]);
                    houseData.setWidthAttr(dimensions[1]);
                    houseData.setHeightAttr(dimensions[2]);
                }
                if (((BigInteger) attr[0]).longValue() == 23L) {
                    houseData.setBedrooms(Integer.valueOf(attr[2].toString()));
                }
            }
        }

    }


    private Map<Long, List<Object[]>> getMappedAttributes(List<Object[]> attributes) {
        return attributes.stream()
                         .collect(
                                 Collectors.groupingBy(
                                         attr -> ((BigInteger) attr[0]).longValue()));
    }

    private List<HouseData> createInitialHouseData(List<Object[]> houses) {
        List<HouseData> initialHouseData = new ArrayList<>();
        houses.forEach(
                house -> {
                    long houseId = ((BigInteger) house[0]).longValue();
                    String name = ((String) house[1]);
                    String city = ((String) house[2]);
                    HouseData houseData = new HouseData(houseId, name, city);
                    initialHouseData.add(houseData);

                }
        );
        return initialHouseData;
    }

    private List<Object[]> getAttributesForHouseIds(List<Long> houseIds) {
        return houseAttributeRepository.getHouseAttributes(houseIds);

    }

    private List<Long> getHousesIds(List<Object[]> houses) {

        return houses.stream()
                     .map(house -> ((BigInteger) house[0]).longValue())
                     .collect(Collectors.toList());


    }

    private List<Object[]> getListOfHouses() {
        return houseRespository.getHouseDataList();
    }
////////////////////////////////


}
