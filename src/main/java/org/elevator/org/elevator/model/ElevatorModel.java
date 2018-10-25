package org.elevator.org.elevator.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.elevator.org.elevator.ElevatorDemo;
import org.elevator.org.elevator.services.ElevatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorModel {
	public final int CAPACITY_WEIGHT = 400;
	public final int FIRST_POSSIBLE_FLOOR = -1;
	public final int LAST_POSSIBLE_FLOOR = 8;
	
	private static Logger logger = LoggerFactory.getLogger(ElevatorDemo.class);

	private int currentFloor;
	private int currentWeight;
	private boolean isFull;
	private boolean isEmpty;
	private Direction direction;
	
	private Set<PersonModel> peopleInside;

	public ElevatorModel(int currentFloor) {
		this.setCurrentFloor(currentFloor);
		this.setFull(false);
		this.setEmpty(true);
		this.peopleInside = new HashSet<>();
	}
	
	public static enum Direction {UP, DOWN}
	
	public void removePeopleByFloor (int floor) {
		peopleInside.stream().filter(p -> p.getTargetFloor() == floor).forEach(p -> {
			try {
				this.removePerson(p);
			} catch (ElevatorException e) {
				e.printStackTrace();
			}
		});
	}

	public void addPerson(PersonModel person) throws ElevatorException {
		if(Objects.nonNull(person)) {
			logger.info("{} gets in", person);
			this.peopleInside.add(person);
			this.currentWeight += person.getWeight();
			this.setEmpty(false);
			if(this.currentWeight == this.CAPACITY_WEIGHT) {
				this.setFull(true);
			}
		} else {
			throw new ElevatorException("nulling is no good!");
		}
	}
	
	private void removePerson(PersonModel person) throws ElevatorException {
		if(Objects.nonNull(person)) {
			logger.info("{} goes out", person);
			this.peopleInside.remove(person);
			this.currentWeight -= person.getWeight();
			if(this.currentWeight == 0) {
				this.setEmpty(true);
			}
			if(this.isFull) {
				this.setFull(false);
			}
		} else {
			throw new ElevatorException("nulling is no good!");
		}
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public int getCurrentWight() {
		return currentWeight;
	}

	public boolean isFull() {
		return isFull;
	}
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public void setCurrentWight(int currentWight) {
		this.currentWeight = currentWight;
	}

	public void setFull(boolean full) {
		this.isFull = full;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
