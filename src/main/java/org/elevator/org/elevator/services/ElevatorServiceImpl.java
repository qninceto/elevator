package org.elevator.org.elevator.services;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.elevator.org.elevator.ElevatorDemo;
import org.elevator.org.elevator.model.ElevatorModel;
import org.elevator.org.elevator.model.ElevatorModel.Direction;
import org.elevator.org.elevator.model.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorServiceImpl implements ElevatorService {

	private static Logger logger = LoggerFactory.getLogger(ElevatorDemo.class);
	private NavigableSet<Integer> requests = new TreeSet<>();
	private Set <PersonModel> waitingPeople;
	
	@Override
	public ElevatorModel createElevator(int startFloor) {
		return new ElevatorModel(startFloor);
	}

	@Override
	public void executeTheCalls(Set<PersonModel> waitingPeople, ElevatorModel elevator) throws ElevatorException {
		if (Objects.isNull(waitingPeople) || waitingPeople.isEmpty() || Objects.nonNull(elevator)) {
			logger.info("There are no requsts or the elevator is fake , the program is terminated!");
			return;
		} else {
			this.waitingPeople = waitingPeople;
			waitingPeople.forEach(p -> requests.add(p.getCurrentFloor()));
			while (!this.waitingPeople.isEmpty() && !elevator.isEmpty()) {
				moveElevator(elevator);
			}
			logger.info("The elevator served it`s purpose, now it`s time to get a whiskey!");
		}
	}

	private void moveElevator(ElevatorModel elevator) throws ElevatorException {
		if (Objects.nonNull(elevator) && Objects.nonNull(this.waitingPeople)) {
			int elevatorCurrentFloor = elevator.getCurrentFloor();
			int downFloor = requests.floor(elevatorCurrentFloor);
			int upFloor = requests.ceiling(elevatorCurrentFloor);
			if (elevator.isEmpty()) {
				if (Math.abs(elevatorCurrentFloor - downFloor) > Math.abs(elevatorCurrentFloor - upFloor)) {
					elevator.setDirection(Direction.UP);
					elevator.setCurrentFloor(upFloor);
				} else {
					elevator.setDirection(Direction.DOWN);
					elevator.setCurrentFloor(downFloor);
				}
			} else {
				if (elevator.getDirection().equals(Direction.DOWN)) {
					elevator.setCurrentFloor(downFloor);
				} else {
					elevator.setCurrentFloor(upFloor);
				}
			}
			stop(elevator);
		} else {
			throw new ElevatorException("Do not dare to give me nulls boy !");
		}
	}

	@Override
	public void stop(ElevatorModel elevator) {
		int stopFloor = elevator.getCurrentFloor();
		if(stopFloor == elevator.FIRST_POSSIBLE_FLOOR) {
			elevator.setDirection(Direction.UP);
		}
		if(stopFloor == elevator.LAST_POSSIBLE_FLOOR) {
			elevator.setDirection(Direction.DOWN);
		}
		if(!elevator.isEmpty()) {
			elevator.removePeopleByFloor(stopFloor);
		}
		this.waitingPeople.forEach(a -> {
			boolean canEnter = (elevator.getCurrentWight() + a.getWeight()) < elevator.CAPACITY_WEIGHT;
			if(a.getCurrentFloor() == stopFloor && canEnter) {
				try {
					elevator.addPerson(a);
				} catch (ElevatorException e) {
					e.printStackTrace();
				}
				this.requests.remove(stopFloor);
				this.waitingPeople.remove(a);}});
	}
}
