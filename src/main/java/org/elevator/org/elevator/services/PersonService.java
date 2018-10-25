package org.elevator.org.elevator.services;

import org.elevator.org.elevator.model.PersonModel;

public interface PersonService {
	PersonModel createPerson(int startFloor, int targetFloor, int weight);

	boolean isValidRequest(int startFloor, int targetFloor);
}
