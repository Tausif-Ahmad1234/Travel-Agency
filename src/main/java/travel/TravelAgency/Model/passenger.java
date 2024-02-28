package travel.TravelAgency.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class passenger {
	//passenger table with same table name and column name for mapping to database
	private String name;
	@Id
	private int passenger_number;
	private String category;
	private int balance;
	private int new_balance;
	public passenger() {
		super();
		// TODO Auto-generated constructor stub
	}
	public passenger(String name, int passenger_number, String category, int balance, int new_balance) {
		super();
		this.name = name;
		this.passenger_number = passenger_number;
		this.category = category;
		this.balance = balance;
		this.new_balance = new_balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassenger_number() {
		return passenger_number;
	}
	public void setPassenger_number(int passenger_number) {
		this.passenger_number = passenger_number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getNew_balance() {
		return new_balance;
	}
	public void setNew_balance(int new_balance) {
		this.new_balance = new_balance;
	}
}
