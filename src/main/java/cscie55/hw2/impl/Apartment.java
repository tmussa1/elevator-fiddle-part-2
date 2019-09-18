package cscie55.hw2.impl;

import cscie55.hw2.exception.TooManyResidentsException;

import java.util.ArrayList;
import java.util.List;

public class Apartment {

	private int id;

	private int floorId;

	public static final int MAX_RESIDENTS = 5;

	private List<Resident> residents = new ArrayList<>();

	public List<Resident> getResidents() {
		return residents;
	}

	/**
	 * Constructor
	 * @param id  the id of the apartment
	 * @param floorId the id of the Floor where this apartment is located.
	 * @see Floor
	 */
	public Apartment(int id, int floorId){
		this.id = id;
		this.floorId = floorId;
	}

	public void addResident(Resident resident) throws TooManyResidentsException {
		if(residents.size() < MAX_RESIDENTS){
			residents.add(resident);
		}
		else{
			throw new TooManyResidentsException();
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}
}
