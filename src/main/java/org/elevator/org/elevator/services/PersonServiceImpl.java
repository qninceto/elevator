package org.elevator.org.elevator.services;

import org.elevator.org.elevator.ElevatorDemo;
import org.elevator.org.elevator.model.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonServiceImpl implements PersonService {

	private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	public PersonModel createPerson(int startFloor, int targetFloor, int weight) {
		return new PersonModel(startFloor, targetFloor, weight);
	}

	@Override
	public boolean isValidRequest(int startFloor, int targetFloor) {
		if (startFloor == targetFloor) {
			logger.info("Invalid request, same direction as the current floor");
			return false;
		}
		return true;
	}

}
