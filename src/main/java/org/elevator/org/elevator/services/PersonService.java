package org.elevator.org.elevator.services;

import org.elevator.org.elevator.model.PersonModel;

public interface PersonService {
	
	/**
	 * creates a new person by given params
	 */
	PersonModel createPerson(int startFloor, int targetFloor, int weight) throws ElevatorException;
	
	/**
	 * performs a check whether the requested floor is the same like the current one,
	 * otherwise terminates the program
	 */
	boolean isValidRequest(int startFloor, int targetFloor);
}
