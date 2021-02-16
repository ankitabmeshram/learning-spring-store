package com.springprep.examples.collections;

import com.springprep.examples.entity.Driver;

import java.util.*;
import java.util.stream.Collectors;

public class streampracticebkp {

    List<Driver> sameAgeDrivers=new ArrayList<>();
    Set<Driver> sameNameDrivers=new HashSet<>();
    Set<Driver> distinctDrivers=new TreeSet<>(Comparator.comparing(
            Driver::getId));
    Map<String,Driver> driversMappedToCompany=new HashMap<>();

    Driver john=new Driver(1,"john",25);
    Driver smith=new Driver(2,"smith",25);
    Driver bob=new Driver(3,"bob",25);
    Driver ranny=new Driver(4,"ranny",25);
    Driver ranny1=new Driver(5,"ranny",26);
    Driver ranny2=new Driver(6,"ranny",27);


    void createSameAgeDrivers(){
        sameAgeDrivers.add(john);
        sameAgeDrivers.add(smith);
        sameAgeDrivers.add(bob);
        sameAgeDrivers.add(ranny);
    }
    void createSameNameDrivers(){
        sameNameDrivers.add(ranny);
        sameNameDrivers.add(ranny1);
        sameNameDrivers.add(ranny2);
    }
    void createdistinctDrivers(){
        distinctDrivers.add(john);
        distinctDrivers.add(smith);
        distinctDrivers.add(bob);
        distinctDrivers.add(ranny);
        distinctDrivers.add(ranny1);
        distinctDrivers.add(ranny2);
    }
    void createDriversMappedToCompany(){
        driversMappedToCompany.put("Tata",john);
        driversMappedToCompany.put("Ford",smith);
        driversMappedToCompany.put("Gm",bob);
    }

    public List<Driver> getDriversOlderThan(Integer age, List<Driver> drivers) {
        return null;
    }

    public void removeDriversOlderThan(Integer age, List<Driver> drivers) {


    }

    public void removeDriversOlderThan2(Integer age, List<Driver> drivers) {
    }

    public Set<String> getSetOfDistinctDriverNames(List<Driver> drivers) {
        return null;
    }

    public List<String> getListOfDistinctDriverNames(List<Driver> drivers) {
        return null;
    }

    public Map<Integer, String> getDriverIdtoNameMap(List<Driver> drivers) {
        return null;
    }

    public Map<String, List<Driver>> getDepartmentNameToDriverMap(List<Driver> drivers) {
        return null;
    }

    //get driver with max age
    public Driver getDriverWIthMaximumAge(List<Driver> drivers) {
        return null;

    }

    //sort drivers by name
    public void sortDriversByName(List<Driver> drivers) {
    }

    //find driver by a given name
    public Driver findDriverByName(List<Driver> drivers, String driverName) {
        return null;
    }


    public void updateDriverAge(List<Driver> drivers, String driverName, Integer newAge) {
    }

    // check if a driver with given id exists or not
    public boolean DriverWithGivenIdExists(List<Driver> drivers, Integer id) {
        return false;
    }

    // check if all drivers in list are older than 5 years
    public boolean allDriversOlderThan5Years(List<Driver> drivers) {

        return false;


    }


    //collect names of all drivers as csv, each driver name should be in uppercase
    public String getNamesOfDrivers(List<Driver> drivers) {
        return null;
    }
}
