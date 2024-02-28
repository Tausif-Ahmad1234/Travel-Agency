package travel.TravelAgency.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class passenger_travel_package {
	//passenger_travel_package table with same table name and column name for mapping to database
	private String package_name;
	private String passenger_name;
	@Id
	private int id;
	private int passenger_number;
	private String activity_name;
	
	private String destination_name;
	
	public passenger_travel_package(String package_name, String passenger_name, int passenger_number, String activity_name, String destination_name) {
		super();
		this.package_name = package_name;
		this.passenger_name = passenger_name;
		this.passenger_number = passenger_number;
		this.activity_name = activity_name;
		this.destination_name = destination_name;
	}
	public passenger_travel_package() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
	public int getPassenger_number() {
		return passenger_number;
	}
	public void setPassenger_number(int passenger_number) {
		this.passenger_number = passenger_number;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	
}
