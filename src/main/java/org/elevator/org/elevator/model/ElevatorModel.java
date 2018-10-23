package org.elevator.org.elevator.model;

import java.util.List;

public class ElevatorModel implements Elevator{
	private static final int CAPACITY_WEIGHT = 400;

	private int currentFloor;
	private int currentWeight;
	private boolean full;

	private List<PersonModel> peopleInside;

	public ElevatorModel(int currentFloor, int currentWeight) {
		super();
		this.currentFloor = currentFloor;
		this.currentWeight = currentWeight;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public int getCurrentWight() {
		return currentWeight;
	}

	public List<PersonModel> getPeopleInside() {
		return peopleInside;
	}

	public boolean isFull() {
		return full;
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public void setCurrentWight(int currentWight) {
		this.currentWeight = currentWight;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public void setPeopleInside(List<PersonModel> peopleInside) {
		this.peopleInside = peopleInside;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
