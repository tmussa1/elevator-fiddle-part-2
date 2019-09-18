package cscie55.hw2.impl;

import cscie55.hw2.api.Passenger;
import cscie55.hw2.api.Person;

public class Resident extends Person implements Passenger {

	private int destinationFloor;
	/**
	 * This class' constructor requires parameters that are passed to the super class.
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	public Resident(String firstName, String lastName, Address address){
		super(firstName,lastName,address);

	}

	public void enterApartment(int apartmentId) {
		//TODO: for another assignment
	}


	/**
	 * returns an int indicating the floor to which the Resident [as Passenger] wants to reach.
	 * @see Passenger#getDestination()
	 */
	public int getDestination() {
		//TODO: return the destination of this Resident as a Passenger
		return 0;
	}


	/**
	 * @see Passenger#setDestination(int)
	 */
	public void setDestination(int destinationFloor) {
		//TODO: set the destination floor
	}

}
