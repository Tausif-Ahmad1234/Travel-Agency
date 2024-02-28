package travel.TravelAgency.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class activity {
	//activity table with same table name and column name for mapping to database
	private String name;
	private String description;
	private int cost;
	private int capacity;
	@Id
	private String destination_name;
	private int new_capacity;
	public activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public activity(String name, String description, int cost, int capacity, String destination_name, int new_capacity) {
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.capacity = capacity;
		this.destination_name = destination_name;
		this.new_capacity = new_capacity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	public int getNew_capacity() {
		return new_capacity;
	}
	public void setNew_capacity(int new_capacity) {
		this.new_capacity = new_capacity;
	}
	@Override
	public String toString() {
		return "Activity name=" + name + ", Description=" + description + ", Cost=" + cost + ", Capacity=" + capacity
				+ ", Destination name=" + destination_name;
	}
}
