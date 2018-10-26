package org.elevator.org.elevator.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.elevator.org.elevator.services.ElevatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorModel {
	public static  final int CAPACITY_WEIGHT = 400;
	public static final int FIRST_POSSIPLE_FLOOR = -1;
	public static final int LAST_POSSIBLE_FLOOR = 8;
	
	private static Logger logger = LoggerFactory.getLogger(ElevatorModel.class);

	private int currentFloor;
	private int currentWeight;
	private boolean isFull;
	private boolean isEmpty;
	private Direction direction;
	
	private Set<PersonModel> peopleInside;

	public ElevatorModel(int currentFloor) throws ElevatorException {
		this.setCurrentFloor(currentFloor);
		this.setFull(false);
		this.setEmpty(true);
		this.peopleInside = new HashSet<>();
	}
	
	public enum Direction {UP, DOWN}
	
	/**
	 * by given floor removes all the people inside the elevator
	 * 
	 * @param floor
	 */
	public void removePeopleByFloor (int floor) {
		peopleInside.removeIf( p -> {
			boolean remove = p.getTargetFloor() == floor;
			try {
				if(remove) {
					this.removePerson(p);
				}
			} catch (ElevatorException e) {
				logger.debug("Something went wrong in the stream bro");
			}
			return remove;});
	}
	
	/**
	 * adds a person inside the elevator 
	 * 
	 * @param person
	 * @throws ElevatorException
	 */
	public void addPerson(PersonModel person) throws ElevatorException {
		if(Objects.nonNull(person)) {
			logger.info("{} gets in", person);
			this.peopleInside.add(person);
			this.currentWeight += person.getWeight();
			this.setEmpty(false);
			if(this.currentWeight == CAPACITY_WEIGHT) {
				this.setFull(true);
			}
		} else {
			throw new ElevatorException("nulling is no good!");
		}
	}
	
	private void removePerson(PersonModel person) throws ElevatorException {
		if(Objects.nonNull(person)) {
			logger.info("{} goes out", person);
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
	
	public void setCurrentFloor(int currentFloor) throws ElevatorException {
		if(currentFloor < LAST_POSSIBLE_FLOOR || currentFloor > FIRST_POSSIPLE_FLOOR) {
			this.currentFloor = currentFloor;
		}else {
			throw new ElevatorException("invalid floor provided");
		}
	}

	public void setCurrentWight(int currentWight) throws ElevatorException {
		if(currentWight > 0) {
			this.currentWeight = currentWight;
		}else {
			throw new ElevatorException("invalid weight provided");
		}
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
