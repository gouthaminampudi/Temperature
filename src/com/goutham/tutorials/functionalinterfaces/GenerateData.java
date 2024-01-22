package com.goutham.tutorials.functionalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;





public class GenerateData {
	
	static Person[] persons = new Person[] {
            new Person("Lenita Kunde", 19, new Address("Chicago", "AZ", "60601"), 32106.8),
            new Person("Mr. Keven Lockman", 23, new Address("Phoenix", "AZ", "10001"), 52686.63),
            new Person("Andreas Hackett", 29, new Address("New York", "IL", "77001"), 48430.8),
            new Person("Tiffanie Douglas", 29, new Address("Los Angeles", "CA", "85001"), 48496.07),
            new Person("Ms. Terrell Mueller", 30, new Address("Houston", "TX", "90001"), 36427.62),
            new Person("Alyce Spinka", 49, new Address("Phoenix", "NY", "90001"), 59794.5),
            new Person("Benjamin Anderson", 51, new Address("New York", "CA", "85001"), 35504.74),
            new Person("Elisabeth Conroy", 52, new Address("Phoenix", "AZ", "77001"), 38270.56),
            new Person("Antwan Hartmann", 61, new Address("Los Angeles", "NY", "77001"), 47541.82),
            new Person("Silvia Morissette DVM", 76, new Address("Houston", "CA", "77001"), 67265.6)
        };

	
	public static List<Person> peopleList = Arrays.asList(persons);

    public static List<Person> getPeopleData() {
        List<String> cities = Arrays.asList("New York", "Los Angeles", "Chicago", "Houston", "Phoenix");
        List<String> states = Arrays.asList("NY", "CA", "IL", "TX", "AZ");
        List<String> zips = Arrays.asList("10001", "90001", "60601", "77001", "85001");

        Random random = new Random();
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            String name = faker.name().fullName();
            int age = random.nextInt(60) + 18; // age between 18 and 78
            double salary = 30000 + (40000 * random.nextDouble());
            salary = Double.parseDouble(String.format("%.2f", salary));// salary between 30000 and 70000
            Address address = new Address(
                cities.get(random.nextInt(cities.size())),
                states.get(random.nextInt(states.size())),
                zips.get(random.nextInt(zips.size()))
            );

            people.add(new Person(name, age, address, salary));
        }

        // Example usage of people list
        //people.forEach(System.out::println);
        return people;
    }
    
    
    
}
