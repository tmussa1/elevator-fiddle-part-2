package cscie55.hw2.impl;

public class Building {

	/**
	The ID variable is static final and represents building ID
	 */
	public static final int ID = 1;

	/**
	The TOTAL_NUM_OF_FLOORS variable is static final.
	 */
	public static final int TOTAL_NUM_OF_FLOORS= 7;

	private Elevator elevator;

	/**
	 * This is all the floors in the building
	 * 
	 */
	private Floor[] floors = new Floor[TOTAL_NUM_OF_FLOORS];


	/**
	 * This constructor creates an Elevator, Floors
	 */
	public Building() {
		createFloors();
		createElevator();
	}

	private void createElevator() {
		this.elevator = new Elevator(floors);
	}

	public void enter() {
	}

	/**
	 * this method must create floor objects and add them to the appropriate index of the floors array
	 */
	private void createFloors(){
		for(int i = 0; i < TOTAL_NUM_OF_FLOORS; i++){
			this.floors[i] = new Floor(i);
		}
	}

	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}

	public Elevator getElevator() {
		return elevator;

	}

	public Floor[] getFloors(){
		return floors;
	}


}
