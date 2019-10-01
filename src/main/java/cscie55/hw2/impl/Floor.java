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
		createApartments();

	}

	private void createApartments() {
		for(int i = 0; i < apartments.length; i++){
			apartments[i] = new Apartment(i , this.id);
		}
	}

	public int getPassengersWaiting() {
		return passengersWaiting;
	}

	/*
	Needed to make this method public to set the number of Passengers waiting on a floor after
	Elevator is full
	 */
	public void setPassengersWaiting(int passengersWaiting) {
		this.passengersWaiting = passengersWaiting;
	}

	public void callElevator(){
		setPassengersWaiting(getPassengersWaiting() + 1);
	}

	public Apartment getApartment(int apartmentNumber) {
		if(apartmentNumber < apartments.length){
			return apartments[apartmentNumber];
		}
		return null;
	}

}
