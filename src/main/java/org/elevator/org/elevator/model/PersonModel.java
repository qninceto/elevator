package org.elevator.org.elevator.model;

public class PersonModel implements Person{
	private int currentFloor;
	private int targetFloor;
	private int weight;
	private String name;
	
	
	public PersonModel(int currentFloor, int targetFloor, int weight) {
		super();
		this.currentFloor = currentFloor;
		this.targetFloor = targetFloor;
		this.weight = weight;
	}
	
	@Override
	public void goIn() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void goOut() {
		// TODO Auto-generated method stub
		
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
