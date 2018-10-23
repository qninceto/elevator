package org.elevator.org.elevator.services;

import java.util.List;

import org.elevator.org.elevator.model.Elevator;
import org.elevator.org.elevator.model.Person;

public interface ElevatorService {
	/**
	 * creates an elevator by the given input data
	 * 
	 * @param currentFloor
	 * @param CurrentWeight
	 * @return elevator
	 */
	Elevator createElevator (int currentFloor, int currentWeight);

	void executeTheCalls(List <Person> people, List <Elevator> elevators);
	
}
