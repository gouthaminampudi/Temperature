package com.goutham.tutorials.functionalinterfaces;

public class Person {
    private String name;
    private int age;
    private Address address;
    private double salary;

    public Person(String name, int age, Address address, double salary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + ", salary=" + salary + "]";
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Address getAddress() {
		return address;
	}

	public double getSalary() {
		return salary;
	}

    
  
    // getters, setters, and toString() as before, with additions for address and salary
}
