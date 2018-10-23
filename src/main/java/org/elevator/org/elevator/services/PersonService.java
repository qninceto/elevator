package org.elevator.org.elevator.services;

import org.elevator.org.elevator.model.Person;

public interface PersonService {
	Person createPerson (int startFloor, int targetFloor, int weight);
}
