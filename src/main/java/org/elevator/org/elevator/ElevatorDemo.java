package org.elevator.org.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.elevator.org.elevator.model.Elevator;
import org.elevator.org.elevator.model.Person;
import org.elevator.org.elevator.services.ElevatorService;
import org.elevator.org.elevator.services.ElevatorServiceImpl;
import org.elevator.org.elevator.services.PersonService;
import org.elevator.org.elevator.services.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorDemo {
	private static Logger logger = LoggerFactory.getLogger(ElevatorDemo.class);

	private static final PersonService personService = new PersonServiceImpl();
	private static final ElevatorService elevatorService = new ElevatorServiceImpl();

	private static List<Person> people = new ArrayList<>();
	private static List<Elevator> elevators = new ArrayList<>();

	public static void main(String[] args) {
		//TODO comma separated input

		Scanner scan = new Scanner(System.in);

		logger.info("Enter number of people: \n");
		int numberOfPeole = scan.nextInt();
		for (int i = 0; i < numberOfPeole; i++) {
			logger.info("Enter the person`s starting floor, desired floor and weight: ");
			int startFloor = scan.nextInt();
			int targetFloor = scan.nextInt();
			int weight = scan.nextInt();
			people.add(personService.createPerson(startFloor, targetFloor, weight));
		}

		logger.info("Enter number of elevators: \n");
		int numberOfElevators = scan.nextInt();
		for (int i = 0; i < numberOfElevators; i++) {
			logger.info("Enter the elevator`s starting floor and current weight: \n");
			int startFloor = scan.nextInt();
			int startWeight = scan.nextInt();
			elevators.add(elevatorService.createElevator(startFloor, startWeight));
		}
		scan.close();

		elevatorService.executeTheCalls(people, elevators);
	}
}
