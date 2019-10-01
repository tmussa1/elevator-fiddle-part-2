package cscie55.hw2.impl;

import cscie55.hw2.api.Passenger;
import cscie55.hw2.exception.ElevatorFullException;
/**
 * @author Tofik Mussa
 * This is the elevator class that moves, picks up passesngers, drops them off and listens to requests
 */
public class Elevator {

	private int[] FLOORS = new int[Building.TOTAL_NUM_OF_FLOORS];
	private int currentFloor = 1;
	private Floor [] passWaitingArray;
	public final static int CAPACITY = 10;

	/**
	 * This is a convenient field to counting cycles
	 */
	private int incrementForEveryFloorTravelled = 1;

	/*
	 * This field tracks direction. It is "UP" when starting out
	 */
	private direction upOrDown;

	private enum direction {UP,DOWN};

	public Elevator(Floor [] passWaitingArray){
		this.passWaitingArray = passWaitingArray;
		this.upOrDown = direction.UP;
	}

	/**
	 * The method takes however many passengers up to its capacity and adjusts the current floor for every method call
	 */
	public void move() {

		this.incrementForEveryFloorTravelled++;

		/**
		Pick up passengers destined to the first floor. The thing here is for passengers that got left trying to board
		 from a floor other than the first floor will still be in the passWaitingArray and get picked up in the next trip
		 */
		if((CAPACITY - getNumberPassengers()) >= passWaitingArray[getCurrentFloor() - 1].getPassengersWaiting()){
			FLOORS[0] += passWaitingArray[getCurrentFloor() - 1].getPassengersWaiting();
		} else {
			FLOORS[0] = CAPACITY;
			int passengersLeftOut= passWaitingArray[getCurrentFloor()].getPassengersWaiting() - CAPACITY;
			passWaitingArray[getCurrentFloor()].setPassengersWaiting(passengersLeftOut);
		}

		/**
		Alternate direction and adjust current floor
		 */
		if((this.incrementForEveryFloorTravelled %
				(2 * Building.TOTAL_NUM_OF_FLOORS)) < Building.TOTAL_NUM_OF_FLOORS){
			this.upOrDown = direction.UP;
			setCurrentFloor(incrementForEveryFloorTravelled % (Building.TOTAL_NUM_OF_FLOORS + 1));
		} else {
			this.upOrDown = direction.DOWN;
			setCurrentFloor(Building.TOTAL_NUM_OF_FLOORS - (this.incrementForEveryFloorTravelled % Building.TOTAL_NUM_OF_FLOORS));
		}

		unloadPassengers();
	}

	/**
	 * @param destination
	 * @throws Elevator full exception when at capacity and increments passenger number otherwise
	 */
	public void boardPassenger(int destination) throws ElevatorFullException {

		if(getNumberPassengers() + 1 > CAPACITY){
			throw new ElevatorFullException("Elevator is at capacity");
		} else {
			incrementPassengersLeadingUptoDestination(destination);
		}

	}

	/**
	 * @param destination
	 * Since the passengers destined to other floors board from the first floor only, we can
	 * increment the number of passengers by 1 up to his/her destination
	 */
	private void incrementPassengersLeadingUptoDestination(int destination) {

		for(int i = 0; i < destination; i++){
			FLOORS[i] += 1;
		}
	}

	/**
	 * @throws Elevator full exception when at capacity and increments passenger number otherwise
	 */
	public void boardPassenger(Passenger passenger) throws ElevatorFullException {

		if(getNumberPassengers() + 1 > CAPACITY){
			throw new ElevatorFullException("Elevator is at capacity");
		} else {
			incrementPassengersLeadingUptoDestination(passenger.getDestination());
		}
	}

	/**
	 * Resets the number of passengers on all floors to zero when the elevator completes a cycle and
	 * hits the ground. It evacuates however many passengers are destined to a floor otherwise
	 */
	private void unloadPassengers() {

		if(getCurrentFloor() == 1 && this.upOrDown == direction.DOWN){
			resetAllPassengersThatBoardedFromOtherFloors();
			this.upOrDown = direction.UP;
		} else if(this.upOrDown == direction.UP){
			int passengersWhoLeft = -(FLOORS[getCurrentFloor()] - FLOORS[getCurrentFloor() - 1]);
			resetPassengersWhoLeft(passengersWhoLeft);
		}
	}

	/**
	 * @param passengersWhoLeft
	 * These passengers boarded from the first floor. They can be destined to any floor so resetting from the floor
	 * they left elevator all the way down to the first floor
	 */
	private void resetPassengersWhoLeft(int passengersWhoLeft) {

		for(int i = getCurrentFloor() - 1; i >= 0; i--){
			FLOORS[i] -= passengersWhoLeft;
		}
	}

	/**
	 * These passengers boarded from their floors. The Elevator has come full circle so unloading them
	 */
	private void resetAllPassengersThatBoardedFromOtherFloors() {
		for(int i = 0; i < Building.TOTAL_NUM_OF_FLOORS; i++){
			FLOORS[i] = 0;
		}
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	private void setCurrentFloor(int floorNum) {
		this.currentFloor = floorNum;
	}

	/**
	 * Since the floor array is indexed starting from zero, we need to adjust for that by doing minus one
	 * @return passengers
	 */
	public int getNumberPassengers() {
		return FLOORS[getCurrentFloor() - 1];
	}


	/**
	 * @return prints current floor and direction
	 */
	@Override
	public String toString() {
		return "Elevator is currently at " + currentFloor +
				" and going " + upOrDown.toString();
	}
}
