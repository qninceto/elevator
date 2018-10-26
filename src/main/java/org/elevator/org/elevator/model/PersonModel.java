package org.elevator.org.elevator.model;

import org.elevator.org.elevator.services.ElevatorException;

public class PersonModel {
	private int currentFloor;
	private int targetFloor;
	private int weight;
	private String name;
	private static int counter;

	public PersonModel(int currentFloor, int targetFloor, int weight) throws ElevatorException {
		this.setCurrentFloor(currentFloor);
		this.setTargetFloor(targetFloor);
		this.setWeight(weight);
		this.name = "Person " + ++counter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonModel other = (PersonModel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public String getName() {
		return name;
	}

	public int getTargetFloor() {
		return targetFloor;
	}

	public int getWeight() {
		return weight;
	}

	public void setCurrentFloor(int currentFloor) throws ElevatorException {
		if(currentFloor < ElevatorModel.LAST_POSSIBLE_FLOOR || currentFloor > ElevatorModel.FIRST_POSSIPLE_FLOOR) {
			this.currentFloor = currentFloor;
		}else {
			throw new ElevatorException("invalid floor provided");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTargetFloor(int targetFloor) throws ElevatorException {
		if(targetFloor < ElevatorModel.LAST_POSSIBLE_FLOOR || targetFloor > ElevatorModel.FIRST_POSSIPLE_FLOOR) {
			this.targetFloor = targetFloor;
		}else {
			throw new ElevatorException("invalid floor provided");
		}
	}

	public void setWeight(int weight) throws ElevatorException {
		if(weight > 0) {
			this.weight = weight;
		}else {
			throw new ElevatorException("invalid weight provided");
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
