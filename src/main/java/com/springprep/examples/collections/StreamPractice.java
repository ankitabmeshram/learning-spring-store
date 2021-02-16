package com.springprep.examples.collections;

import com.springprep.examples.entity.Driver;

import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {

    private List<Driver> sameAgeDrivers = new ArrayList<>();
    private Set<Driver> sameNameDrivers = new HashSet<>();
    private Set<Driver> distinctDrivers = new TreeSet<>(Comparator.comparing(
            Driver::getId));
    private Map<String, Driver> driversMappedToCompany = new HashMap<>();

    private Driver john = new Driver(1, "john", 25);
    private Driver smith = new Driver(2, "smith", 25);
    private Driver bob = new Driver(3, "bob", 25);
    private Driver ranny = new Driver(4, "ranny", 25);
    private Driver ranny1 = new Driver(5, "ranny", 26);
    private Driver ranny2 = new Driver(6, "ranny", 27);
    private Driver ankita = new Driver(7, "Ankita", 31);


    void createSameAgeDrivers() {
        sameAgeDrivers.add(john);
        sameAgeDrivers.add(smith);
        sameAgeDrivers.add(bob);
        sameAgeDrivers.add(ranny);
    }

    void createSameNameDrivers() {
        sameNameDrivers.add(ranny);
        sameNameDrivers.add(ranny1);
        sameNameDrivers.add(ranny2);
    }

    void createdistinctDrivers() {
        distinctDrivers.add(john);
        distinctDrivers.add(smith);
        distinctDrivers.add(bob);
        distinctDrivers.add(ranny);
        distinctDrivers.add(ranny1);
        distinctDrivers.add(ranny2);
    }

    void createDriversMappedToCompany() {
        driversMappedToCompany.put("Tata", john);
        driversMappedToCompany.put("Ford", smith);
        driversMappedToCompany.put("Gm", bob);
    }

    void listOperations() {

        sameAgeDrivers.add(john);
        sameAgeDrivers.addAll(sameNameDrivers);
        List<Driver> copied = new ArrayList<>();
        Collections.copy(copied, sameAgeDrivers);


        sameAgeDrivers.remove(2);
        sameAgeDrivers.removeAll(sameNameDrivers);
        sameAgeDrivers.clear();


        sameAgeDrivers.contains(john);
        sameAgeDrivers.containsAll(sameNameDrivers);

        sameAgeDrivers.size();


        sameAgeDrivers.sort(Comparator.comparing(d -> d.getName()));
        Driver driver = sameAgeDrivers.get(1);
        int i = sameAgeDrivers.indexOf(john);
        sameAgeDrivers.set(1, john);


    }

    void setOperations(){

        sameNameDrivers.add(john);
        sameNameDrivers.addAll(distinctDrivers);
        sameNameDrivers.remove(john);
        sameNameDrivers.removeAll(distinctDrivers);
        sameNameDrivers.clear();
        sameNameDrivers.size();
        sameNameDrivers.contains(john);
        sameNameDrivers.containsAll(distinctDrivers);


    }

    void mapOperations(){
     driversMappedToCompany.put("Ford",ankita);
     driversMappedToCompany.remove("Tata");
    driversMappedToCompany.clear();

    driversMappedToCompany.replace("Dodge",john,bob);;

    driversMappedToCompany.size();


    driversMappedToCompany.containsKey("Tata");
    driversMappedToCompany.containsValue(john);
    driversMappedToCompany.values();
    driversMappedToCompany.keySet();
    driversMappedToCompany.entrySet();
    driversMappedToCompany.get("tata");

    }


    public List<Driver> getDriversOlderThan(Integer age, List<Driver> drivers) {
        return drivers.stream()
                      .filter(driver -> driver.getAge() > age)
                      .collect(
                              Collectors.toList());
    }

    public void removeDriversOlderThan(Integer age, List<Driver> drivers) {

        drivers.removeIf(driver -> driver
                .getAge() > age);

    }

    public void removeDriversOlderThan2(Integer age, List<Driver> drivers) {

        drivers.removeIf(driver -> driver.getAge() > age);
    }

    public Set<String> getSetOfDistinctDriverNames(List<Driver> drivers) {
        return drivers.stream()
                      .map(Driver::getName).distinct()
                      .collect(Collectors.toSet());
    }

    public List<String> getListOfDistinctDriverNames(List<Driver> drivers) {
        return drivers.stream()
                      .map(Driver::getName)
                      .distinct()
                      .collect(Collectors.toList());
    }

    public Map<Integer, String> getDriverIdtoNameMap(List<Driver> drivers) {
        return drivers.stream()
                      .collect(Collectors.toMap(Driver::getId,
                              Driver::getName));


    }

    public Map<String, List<Driver>> getDepartmentNameToDriverMap(List<Driver> drivers) {
        return
                drivers.stream()
                       .collect(Collectors.groupingBy(
                               Driver::getName));
    }

    //get driver with max age
    public Driver getDriverWIthMaximumAge(List<Driver> drivers) {
        Optional<Driver> driver = drivers.stream()
                                         .max(Comparator.comparing(
                                                 Driver::getAge));
        return driver.orElse(null);


    }

    //sort drivers by name
    public void sortDriversByName(List<Driver> drivers) {
        drivers.sort(Comparator.comparing(Driver::getAge));
    }

    //find driver by a given name
    public Driver findDriverByName(List<Driver> drivers, String driverName) {
        Optional<Driver> driverFound
                = drivers.stream()
                         .filter(driver -> driver.getName()
                                                 .equals(driverName))
                         .findAny();
        return driverFound.orElse(null);

    }


    public void updateDriverAge(List<Driver> drivers, String driverName, Integer newAge) {
        drivers.stream()
               .filter(driver -> driver.getName()
                                       .equals(driverName))
               .findAny()
               .ifPresent(driver -> driver.setAge(newAge));
    }

    // check if a driver with given id exists or not
    public boolean DriverWithGivenIdExists(List<Driver> drivers, Integer id) {
        return drivers.stream()
                      .anyMatch(driver -> driver.getId() == id);
    }

    // check if all drivers in list are older than 5 years
    public boolean allDriversOlderThan5Years(List<Driver> drivers) {

        return drivers.stream()
                      .allMatch(driver -> driver.getAge() > 5);


    }


    //collect names of all drivers as csv, each driver name should be in uppercase
    public String getNamesOfDrivers(List<Driver> drivers) {
        return drivers.stream()
                      .map(d -> d.getName()
                                 .toUpperCase())
                      .collect(Collectors.joining(","));
    }
}
