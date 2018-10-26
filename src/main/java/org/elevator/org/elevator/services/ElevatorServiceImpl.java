package org.elevator.org.elevator.services;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.elevator.org.elevator.model.ElevatorModel;
import org.elevator.org.elevator.model.ElevatorModel.Direction;
import org.elevator.org.elevator.model.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorServiceImpl implements ElevatorService {

	private static Logger logger = LoggerFactory.getLogger(ElevatorServiceImpl.class);
	private NavigableSet<Integer> requests = new TreeSet<>();
	private Set <PersonModel> waitingPeople;
	
	/**
	 * creates an elevator by the given input data
	 * 
	 * @param currentFloor
	 * @param CurrentWeight
	 * @return elevator
	 * @throws ElevatorException 
	 */
	@Override
	public ElevatorModel createElevator(int startFloor) throws ElevatorException {
		return new ElevatorModel(startFloor);
	}
	
	/**
	 * creates an elevator by the given input data
	 * 
	 * @param currentFloor
	 * @param CurrentWeight
	 * @return elevator
	 * @throws ElevatorException 
	 */
	@Override
	public void executeTheCalls(Set<PersonModel> waitingPeople, ElevatorModel elevator) throws ElevatorException {
		if (Objects.isNull(waitingPeople) || waitingPeople.isEmpty() || Objects.isNull(elevator)) {
			logger.info("There are no requsts or the elevator is fake , the program is terminated!");
		} else {
			this.waitingPeople = waitingPeople;
			waitingPeople.forEach(p -> requests.add(p.getCurrentFloor()));
			while (!this.waitingPeople.isEmpty() || !elevator.isEmpty()) {
				moveElevator(elevator);
			}
			logger.info("The elevator served it`s purpose, now it`s time to get a whiskey!");
		}
	}
	
	/**
	 * implements the logic and the algorithm of the elevators movement
	 * if the elevator is empty it finds the closest to it requested floor 
	 * and moves towards that direction, then it keeps moving the same 
	 * direction until it gets to the threshold floor or gets empty again. 
	 * 	 
	 * @param elevator
	 * @throws ElevatorException
	 */
	@Override
	public void moveElevator(ElevatorModel elevator) throws ElevatorException {
		if (Objects.nonNull(elevator) && Objects.nonNull(this.waitingPeople)) {
			int elevatorCurrentFloor = elevator.getCurrentFloor();
			Integer downFloor = requests.floor(elevatorCurrentFloor);
			Integer upFloor = requests.ceiling(elevatorCurrentFloor);
			int targetFloor = getTargetFloor(downFloor, upFloor, elevatorCurrentFloor);
			if (elevator.isEmpty()) {
				if(targetFloor > elevatorCurrentFloor) {
					logger.info("The elevator moves up to floor {}", upFloor);
					elevator.setDirection(Direction.UP);
					elevator.setCurrentFloor(upFloor);
				}
				if(targetFloor < elevatorCurrentFloor) {
					logger.info("The elevator moves down to floor {}", downFloor);
					elevator.setDirection(Direction.DOWN);
					elevator.setCurrentFloor(downFloor);
				}
			} else {
				if (elevator.getDirection().equals(Direction.DOWN)) {
					logger.info("The elevator moves down to floor {}", downFloor);
					elevator.setCurrentFloor(downFloor);
				} else {
					logger.info("The elevator moves up to floor {}", upFloor);
					elevator.setCurrentFloor(upFloor);
				}
			}
			stop(elevator);
		} else {
			throw new ElevatorException("Do not dare to give me nulls boy !");
		}
	}

	private int getTargetFloor(Integer downFloor, Integer upFloor, int elevatorCurrentFloor) throws ElevatorException {
		if(Objects.nonNull(upFloor) && Objects.nonNull(downFloor)) {
			return Math.abs(elevatorCurrentFloor - downFloor) < Math.abs(elevatorCurrentFloor - upFloor) ? downFloor : upFloor;
		}
		if(Objects.isNull(downFloor)) {
			return upFloor;
		}
		if(Objects.isNull(upFloor)) {
			return downFloor;
		}
		throw new ElevatorException("problems with calculations....sorryyyyy");
	}

	/**
	 * stops the elevator on a certain floor
	 * 
	 * @param elevator
	 */
	@Override
	public void stop(ElevatorModel elevator) {
		int stopFloor = elevator.getCurrentFloor();
		logger.info("Opens on floor {}", stopFloor);
		if(stopFloor == ElevatorModel.FIRST_POSSIPLE_FLOOR) {
			elevator.setDirection(Direction.UP);
		}
		if(stopFloor == ElevatorModel.LAST_POSSIBLE_FLOOR) {
			elevator.setDirection(Direction.DOWN);
		}
		if(!elevator.isEmpty()) {
			elevator.removePeopleByFloor(stopFloor);
			this.requests.remove(stopFloor);
		}
		this.waitingPeople.forEach(a -> {
			boolean canEnter = (elevator.getCurrentWight() + a.getWeight()) < ElevatorModel.CAPACITY_WEIGHT;
			if(a.getCurrentFloor() == stopFloor && canEnter) {
				try {
					elevator.addPerson(a);
				} catch (ElevatorException e) {
					e.printStackTrace();
				}
				this.requests.add(a.getTargetFloor());
				this.requests.remove(stopFloor);
				this.waitingPeople.remove(a);}});
	}
}
