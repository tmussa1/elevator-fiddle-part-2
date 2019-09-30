package cscie55.hw2.impl;

public class Building {

	/*
	The ID variable is static final. We will not have more than one building.
	 */
	public static final int ID = 1;

	/*
	The TOTAL_NUM_OF_FLOORS variable is static final. We will not add any more stories.
	 */
	public static final int TOTAL_NUM_OF_FLOORS= 7;

	private Elevator elevator;

	/**
	 * 
	 * 
	 */
	private Floor[] floors = new Floor[TOTAL_NUM_OF_FLOORS];


	/**
	 * This constructor must create an Elevator, Floors
	 */
	public Building() {
		// TODO: create an elevator
		createFloors();
		createElevator();
	}

	private void createElevator() {
		this.elevator = new Elevator(floors);
	}

	public void enter() {
	    // for future Assignment
	}

	/**
	 * this method must create floor objects and add them to the appropriate index of the floors array
	 */
	private void createFloors(){
		//TODO: Implement
		for(int i = 0; i < TOTAL_NUM_OF_FLOORS; i++){
			this.floors[i] = new Floor(i);
		}
	}

	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}

	public Elevator getElevator() {
		// TODO:
		return elevator;

	}

	public Floor[] getFloors(){
		// TODO:
		return floors;
	}


}
