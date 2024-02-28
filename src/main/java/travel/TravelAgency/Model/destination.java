package travel.TravelAgency.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class destination {
	//destination table with same table name and column name for mapping to database
	@Id
	private String name;
	private String activity_name;
	public destination() {
		super();
		// TODO Auto-generated constructor stub
	}
	public destination(String name, String activity_name) {
		super();
		this.name = name;
		this.activity_name = activity_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
}
