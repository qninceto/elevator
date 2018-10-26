package org.elevator.org.elevator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.elevator.org.elevator.model.ElevatorModel;
import org.elevator.org.elevator.model.PersonModel;
import org.elevator.org.elevator.services.ElevatorException;
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

	private static Set<PersonModel> people = new HashSet<>();
	private static ElevatorModel elevator;

	public static void main(String[] args) throws ElevatorException {

		Scanner scan = new Scanner(System.in);

		logger.info("Enter the elevator`s starting floor: ");
		int startFloor = scan.nextInt();
		elevator = elevatorService.createElevator(startFloor);

		logger.info("Enter number of people: ");
		int numberOfPeole = scan.nextInt();
		int targetFloor;
		int weight;
		for (int i = 0; i < numberOfPeole; i++) {
			logger.info("Enter the person`s starting floor, desired floor and weight //space separated: ");
			startFloor = scan.nextInt();
			targetFloor = scan.nextInt();
			weight = scan.nextInt();
			if(personService.isValidRequest(startFloor, targetFloor)) {
				people.add(personService.createPerson(startFloor, targetFloor, weight));
			}
		}

		scan.close();
		elevatorService.executeTheCalls(people, elevator);
	}
}
