package cscie55.hw2;

import cscie55.hw2.impl.Apartment;
import cscie55.hw2.impl.Building;
import cscie55.hw2.impl.Elevator;
import cscie55.hw2.impl.Floor;
import cscie55.hw2.impl.Resident;
import cscie55.hw2.impl.Address;

import static org.junit.Assert.assertEquals;

public class Bootstrap {

	/**
	 * At present BootStrap doesn't really do anything
	 * @param args
	 */
	public static void main(String[] args) {
		Building b = new Building();

		Building building = new Building();
		Elevator elevator = building.getElevator();
		// Have enough people waiting on the 4th floor to exceed elevator capacity by 50%
		int waiting = (int) (Elevator.CAPACITY * 1.5);
		for (int i = 0; i < waiting; i++) {
			building.getFloor(4).callElevator();
		}
		// Move to 4, checking state
		elevator.move();
		checkElevator(elevator, 2, 0);
		elevator.move();
		checkElevator(elevator, 3, 0);
		elevator.move();
		// Should have filled the elevator, leaving people on 4
		checkElevator(elevator, 4, Elevator.CAPACITY);
		assertEquals(waiting - Elevator.CAPACITY, building.getFloor(4).getPassengersWaiting());
		// Get to the ground floor
		while (elevator.getCurrentFloor() != 1) {
			elevator.move();
			if (elevator.getCurrentFloor() == 1) {
				checkElevator(elevator, elevator.getCurrentFloor(), 0);
			} else {
				checkElevator(elevator, elevator.getCurrentFloor(), Elevator.CAPACITY);
			}
		}
		// Go back to 4
		while (elevator.getCurrentFloor() != 4) {
			elevator.move();
			if (elevator.getCurrentFloor() == 4) {
				// Check to see that the remaining passengers boarded
				checkElevator(elevator, elevator.getCurrentFloor(), waiting - Elevator.CAPACITY);
				assertEquals(0, building.getFloor(4).getPassengersWaiting());
			} else {
				checkElevator(elevator, elevator.getCurrentFloor(), 0);
			}
		}
	}

	//utility method just checks the results
	private static void checkElevator(Elevator elevator, int floorNumber, int passengers)
	{
		assertEquals(floorNumber, elevator.getCurrentFloor());
		assertEquals(passengers, elevator.getNumberPassengers());
	}


}
