package travel.TravelAgency.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class travel_package {
	//travel_package table with same table name and column name for mapping to database
	@Id
	private String name;
	private int passenger_capacity;
	private String itinerary;
	private int new_capacity;
	public travel_package() {
		super();
		// TODO Auto-generated constructor stub
	}
	public travel_package(String name, int passenger_capacity, String itinerary, int new_capacity) {
		super();
		this.name = name;
		this.passenger_capacity = passenger_capacity;
		this.itinerary = itinerary;
		this.new_capacity = new_capacity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassenger_capacity() {
		return passenger_capacity;
	}
	public void setPassenger_capacity(int passenger_capacity) {
		this.passenger_capacity = passenger_capacity;
	}
	public String getItinerary() {
		return itinerary;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public int getNew_capacity() {
		return new_capacity;
	}
	public void setNew_capacity(int new_capacity) {
		this.new_capacity = new_capacity;
	}
}
