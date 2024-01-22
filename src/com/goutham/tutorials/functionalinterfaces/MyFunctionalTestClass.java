package com.goutham.tutorials.functionalinterfaces;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyFunctionalTestClass {
	
	public static void main(String args[]) {
		
		MyFunctionalVoidInterface myFunctionalVoidInterface =(firstString, secondString)->{
			System.out.println(firstString+" : "+secondString);
		};
		myFunctionalVoidInterface.add("Goutham","Inampudi");
		
		
		List<Person> peopleList = GenerateData.getPeopleData();
		
		//peopleList.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName)).collect(Collectors.toList()).forEach(System.out::println);

	
		//get count of all persons with age greater then 25
		long greaterThenTwentyFive = peopleList.stream().filter((t)->t.getAge()>25).count();

		//get the person with max salary
		Optional<Person> maxPerson = peopleList.stream().max(Comparator.comparingDouble(Person::getSalary));
		System.out.println(maxPerson.get().getSalary());
		
		
		//get a a count for number of persons in each state
		Map<String,List<Person>> grouppeopleByStateList = peopleList.stream().collect(Collectors.groupingBy(p->p.getAddress().getState()));
		grouppeopleByStateList.entrySet().forEach(t->{
		    String state = t.getKey();
		    int count = t.getValue().size();
		    System.out.println("State: " + state + ", Count: " + count);
		});
		
		//get the max salary in each state.
		Comparator<Person> comparatorByState = Comparator.comparing(person -> person.getAddress().getState());
		Map<String,Double> getMaxSalaryInEachState = peopleList.stream().
														//sorted(comparatorByState).
															collect(Collectors.groupingBy(p->p.getAddress().getState(),
														Collectors.collectingAndThen(
															Collectors.maxBy(Comparator.comparing(Person::getSalary)),
															op->op.map(Person::getSalary).orElse(0.0))));

															getMaxSalaryInEachState.forEach((state, salary)->System.out.println("Max salary in " + state + " is: " + salary) );

	}
}

	
	

