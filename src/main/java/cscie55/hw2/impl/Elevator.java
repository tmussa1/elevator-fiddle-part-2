package cscie55.hw2.impl;

import cscie55.hw2.api.Passenger;
import cscie55.hw2.exception.ElevatorFullException;

public class Elevator {

	private int[] FLOORS = new int[Building.TOTAL_NUM_OF_FLOORS];
	private int currentFloor;
	public final static int CAPACITY = 10;
	private enum direction {UP,DOWN};

	public Elevator(){
		currentFloor = 0;
	}
	public void move() {
		// TODO: as in HW 1, the elevator moves up, dropping passengers along the way

	}

	public void boardPassenger(int destination) throws ElevatorFullException {
		//TODO: create method to allow an integer to increment the destination floor

	}
	public void boardPassenger(Passenger passenger) throws ElevatorFullException {
		//TODO: overloaded method: get destination of passenger and add it to num destined to that floor

	}

	private void unloadPassengers() {
		//TODO: remove passengers destined for the floor at this stop

	}

	public int getCurrentFloor() {
		//TODO: impl... replace the 0 with the value of the currentFloor
		return 0;
	}

	private void setCurrentFloor(int floorNum) {
		// TODO: Optional. might be handy. Delete if you don't want it
	}

	public String toString() {
		String s ="";
		// TODO: impl toString()
		return s;
	}

	public int getNumberPassengers() {
		//TODO: store number [or sum up number] of passengers and return
		return 0;
	}

}
