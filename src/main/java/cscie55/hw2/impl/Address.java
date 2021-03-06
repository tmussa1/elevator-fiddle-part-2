package cscie55.hw2.impl;


public class Address {

	/**
	 * buildingId in the Address object
	 */
	private int buildingId;


	/**
	 * the id of the Floor where the Apartment is situated
	 */

	private int floorId;

	/**
	 * Id of this Apartment
	 */
	private int apartmentId;


	public Address(int buildingId, int floorId, int apartmentId){
		this.buildingId = buildingId;
		this.floorId = floorId;
		this.apartmentId = apartmentId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}
}
