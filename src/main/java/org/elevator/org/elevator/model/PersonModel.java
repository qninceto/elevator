package org.elevator.org.elevator.model;

public class PersonModel {
	private int currentFloor;
	private int targetFloor;
	private int weight;
	private String name;
	private static int counter;

	public PersonModel(int currentFloor, int targetFloor, int weight) {
		this.currentFloor = currentFloor;
		this.targetFloor = targetFloor;
		this.weight = weight;
		this.name = "Person" + ++counter;
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

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTargetFloor(int targetFloor) {
		this.targetFloor = targetFloor;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
