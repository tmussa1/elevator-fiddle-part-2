package cscie55.hw2;

import cscie55.hw2.api.Passenger;
import cscie55.hw2.exception.ElevatorFullException;
import cscie55.hw2.impl.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BootstrapTest extends Bootstrap {

    Building building;
    Resident resident1;
    Resident resident2;
    Address a;

    @Before
    public void setUp() throws Exception {
        building = new Building();
        a = new Address(Building.ID,2,4);
        resident1 = new Resident("Lucy","MacGillicuty",a);
        resident2 = new Resident("Rickie","Ricardo",a);
        ((Passenger) resident1).setDestination(resident1.getAddress().getFloorId());
        ((Passenger) resident2).setDestination(resident2.getAddress().getFloorId());
    }
    @Test
    public void testElevatorCreation() {
        Elevator elevator = building.getElevator();
        assertEquals(0,elevator.getNumberPassengers());
    }

    @Test
    public void testElevatorToString() {
        Elevator elevator = building.getElevator();
        assertEquals("Elevator is currently at " + 1 +
                " and going " + "UP",elevator.toString());
    }
    // Don't board any passengers. Just check that the elevator moves up and down correctly.
    @Test
    public void testElevatorMotion()
    {
        Building building = new Building();
        Elevator elevator = building.getElevator();
        int expectedFloorNumber = 1;
        // Go to the top
        while (expectedFloorNumber < Building.TOTAL_NUM_OF_FLOORS) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber++;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(Building.TOTAL_NUM_OF_FLOORS, expectedFloorNumber);
        // Go back to the bottom
        while (expectedFloorNumber > 1) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber--;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(1, expectedFloorNumber);
    }

    // Check that passengers get on and off correctly.
    @Test
    public void testLoadUnload() throws ElevatorFullException
    {
        Building building = new Building();
        Elevator elevator = building.getElevator();
        checkElevator(elevator, 1, 0);
        // Add passengers and disembark them on the way up
        elevator.boardPassenger(3);
        elevator.boardPassenger(4);
        elevator.boardPassenger(4);
        elevator.boardPassenger(6);
        elevator.boardPassenger(6);
        elevator.boardPassenger(6);
        checkElevator(elevator, 1, 6);
        elevator.move();
        checkElevator(elevator, 2, 6);
        elevator.move();
        checkElevator(elevator, 3, 5);
        elevator.move();
        checkElevator(elevator, 4, 3);
        elevator.move();
        checkElevator(elevator, 5, 3);
        elevator.move();
        checkElevator(elevator, 6, 0);
        elevator.move();
        checkElevator(elevator, 7, 0);
    }

    @Test
    public void elevatorFull()
    {
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
    @Test
    public void testFloorCreation() {
        Floor[] floors = building.getFloors();
        assert(floors.length==7);
    }

    @Test
    public void testApartmentCreation() {
        Floor[] floors = building.getFloors();
        Apartment apartment = floors[1].getApartment(0);
        assert(apartment.getId() == 0);
        assert(apartment.getResidents().size() == 0);
    }


    @Test
    public void testElevatorBoardPassenger() {
        Elevator elevator = building.getElevator();
        try {
            elevator.boardPassenger((Passenger) resident1);
            elevator.boardPassenger((Passenger) resident2);
        }
        catch(ElevatorFullException e){
            System.out.println(e.getMessage());
        }
        assertEquals(2, elevator.getNumberPassengers());
    }

    //utility method just checks the results
    private void checkElevator(Elevator elevator, int floorNumber, int passengers)
    {
        assertEquals(floorNumber, elevator.getCurrentFloor());
        assertEquals(passengers, elevator.getNumberPassengers());
    }
}