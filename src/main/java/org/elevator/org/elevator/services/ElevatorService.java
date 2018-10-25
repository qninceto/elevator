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
	 */
	ElevatorModel createElevator(int currentFloor);

	void executeTheCalls(Set<PersonModel> waitingPeople, ElevatorModel elevator) throws ElevatorException;

	void stop(ElevatorModel elevator);

}
