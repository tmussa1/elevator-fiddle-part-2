package cscie55.hw2.impl;

public class Floor {

	private int id;

	private Apartment[] apartments = new Apartment[4];

	private int passengersWaiting;

	/**
	 *
	 * @param id the id of this Floor. Note that the Building has a max of 7 FLoors
	 * @see Building, Building.TOTAL_NUM_OF_FLOORS
	 */
	public Floor(int id){
		this.id = id;
		//TODO: Create 4 Apartments for this floor (that is, each floor will have 4 Apartments)
		createApartments();

	}

	private void createApartments() {
		for(int i = 0; i < apartments.length; i++){
			apartments[i] = new Apartment(i , this.id);
		}
	}

	public int getPassengersWaiting() {
		// TODO: implement a method that returns the number of people who have called the Elevator
		return passengersWaiting;
	}

	/*
	Needed to make this method public to set the number of Passengers waiting on a floor after
	Elevator is full
	 */
	public void setPassengersWaiting(int passengersWaiting) {
		// TODO: implement a method that returns the number of people who have called the Elevator
		this.passengersWaiting = passengersWaiting;
	}

	public void callElevator(){
		// TODO: implement a field that can hold the number of passengers who want to get on the Elevator on this floor
		// TODO: use this method to add to that number.
		setPassengersWaiting(getPassengersWaiting() + 1);
	}

	public Apartment getApartment(int apartmentNumber) {
		//TODO: add check to ensure requested apartment number exists
		if(apartmentNumber < apartments.length){
			return apartments[apartmentNumber];
		}
		return null;
	}

}
