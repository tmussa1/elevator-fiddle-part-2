package cscie55.hw2.impl;

import cscie55.hw2.api.Passenger;
import cscie55.hw2.exception.ElevatorFullException;
/*

 */
public class Elevator {

	private int[] FLOORS = new int[Building.TOTAL_NUM_OF_FLOORS];
	private int currentFloor = 1;
	private Floor [] passWaitingArray;
	public final static int CAPACITY = 10;
	private int incrementForEveryFloorTravelled = 1;
	private direction upOrDown;
	private enum direction {UP,DOWN};

	public Elevator(Floor [] passWaitingArray){
		this.passWaitingArray = passWaitingArray;
		this.upOrDown = direction.UP;
	}
	public void move() {

		this.incrementForEveryFloorTravelled++;

		if((CAPACITY - getNumberPassengers()) >= passWaitingArray[getCurrentFloor() - 1].getPassengersWaiting()){
			FLOORS[0] += passWaitingArray[getCurrentFloor() - 1].getPassengersWaiting();
		} else {
			FLOORS[0] += (CAPACITY - getNumberPassengers());
			int passengersLeftOut= (passWaitingArray[getCurrentFloor() - 1].getPassengersWaiting() + getNumberPassengers()) - CAPACITY;
			passWaitingArray[getCurrentFloor()].setPassengersWaiting(passengersLeftOut);
		}

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

	public void boardPassenger(int destination) throws ElevatorFullException {

		if(getNumberPassengers() + 1 > CAPACITY){
			throw new ElevatorFullException("Elevator is at capacity");
		} else {
			incrementPassengersLeadingUptoDestination(destination);
		}

	}

	private void incrementPassengersLeadingUptoDestination(int destination) {

		for(int i = 0; i < destination; i++){
			FLOORS[i] += 1;
		}
	}

	public void boardPassenger(Passenger passenger) throws ElevatorFullException {

		if(getNumberPassengers() + 1 > CAPACITY){
			throw new ElevatorFullException("Elevator is at capacity");
		} else {
			incrementPassengersLeadingUptoDestination(passenger.getDestination());
		}
	}

	private void unloadPassengers() {

		if(getCurrentFloor() == 1 && this.upOrDown == direction.DOWN){
			resetAllPassengersThatBoardedFromOtherFloors();
			this.upOrDown = direction.UP;
		} else if(this.upOrDown == direction.UP){
			int passengersWhoLeft = -(FLOORS[getCurrentFloor()] - FLOORS[getCurrentFloor() - 1]);
			resetPassengersWhoLeft(passengersWhoLeft);
		}
	}

	/*
	These passengers boarded from the first floor. They can be destined to any floor so resetting from the floor
	they left elevator all the way down to the first floor
	 */
	private void resetPassengersWhoLeft(int passengersWhoLeft) {

		for(int i = getCurrentFloor() - 1; i >= 0; i--){
			FLOORS[i] -= passengersWhoLeft;
		}
	}

	/*
	These passengers boarded from ther floors. The Elevator has come full circle so unloading them
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

	public int getNumberPassengers() {
		return FLOORS[getCurrentFloor() - 1];
	}

	@Override
	public String toString() {
		return "Elevator is currently at " + currentFloor +
				" and going " + upOrDown.toString();
	}
}
