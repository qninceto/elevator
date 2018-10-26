package org.elevator.org.elevator.services;

import java.util.Set;

import org.elevator.org.elevator.model.ElevatorModel;
import org.elevator.org.elevator.model.PersonModel;

public interface ElevatorService {
	/**
	 * creates an elevator by the given input data
	 * 
	 * @param currentFloor
	 * @param CurrentWeight
	 * @return elevator
	 * @throws ElevatorException 
	 */
	ElevatorModel createElevator(int currentFloor) throws ElevatorException;
	
	/**
	 * by given collection of people with requests and elevator,
	 * executes the elevator service
	 * 
	 * @param waitingPeople
	 * @param elevator
	 * @throws ElevatorException
	 */
	void executeTheCalls(Set<PersonModel> waitingPeople, ElevatorModel elevator) throws ElevatorException;
	
	/**
	 * stops the elevator on a certain floor
	 * 
	 * @param elevator
	 */
	void stop(ElevatorModel elevator);
	
	/**
	 * implements the logic and the algorithm of the elevators movement
	 * 
	 * @param elevator
	 * @throws ElevatorException
	 */
	void moveElevator(ElevatorModel elevator) throws ElevatorException;

}
