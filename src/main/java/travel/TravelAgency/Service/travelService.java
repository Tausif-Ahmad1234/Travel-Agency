package travel.TravelAgency.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.TravelAgency.DAO.activityDAO;
import travel.TravelAgency.DAO.destinationDAO;
import travel.TravelAgency.DAO.passengerDAO;
import travel.TravelAgency.DAO.passenger_travel_packageDAO;
import travel.TravelAgency.DAO.travel_packageDAO;
import travel.TravelAgency.Model.activity;
import travel.TravelAgency.Model.passenger;
import travel.TravelAgency.Model.passenger_travel_package;
import travel.TravelAgency.Model.travel_package;

@Service
public class travelService {
	
	@Autowired
	private activityDAO actDAO;
	@Autowired
	private destinationDAO destDAO;
	@Autowired
	private passenger_travel_packageDAO passTravelPackageDAO;	
	@Autowired
	private passengerDAO passDAO;
	@Autowired
	private travel_packageDAO travPackageDAO;
	
	//method to add passenger in database
	public int addPassenger(String passengerName,int passenger_Number,String passengerCategory,int balance,String packageName,List<String> activitySelected) {
		Optional<travel_package> x = travPackageDAO.findById(packageName);
		travel_package i = x.get();
		int originalPassengerCapacity = i.getPassenger_capacity();//getting passenger capacity for a particular package
		int newPassengerCapacity = i.getNew_capacity();//getting filled passenger capacity of package
		//checking if package passenger capacity full or not; if it is full return 0 else 1	
		if(newPassengerCapacity<originalPassengerCapacity) {
			passenger p = new passenger(passengerName,passenger_Number,passengerCategory,balance,balance);
			passDAO.save(p);//adding passenger to passenger DB		
			String itinerary = i.getItinerary();
			String[] itineraryArray = itinerary.split("-");//extracting destinations for selected package
			//iterating destination and selected activity against one another			
			for(String dest:itineraryArray) {
				for(String a:activitySelected) {
					//checking if passenger has selected this activity
					if(a!=null) {
						//checking if destination offers that activity; returns 1 if offers else 0
						if(actDAO.checkCombination(dest, a)==1) {
							int newActivityCapacity = actDAO.getNewCapacity(dest, a);//getting filled capacity for an activity
							Optional<activity> y = actDAO.findById(dest);
							activity q = y.get();
							int originalActivityCapacity = q.getCapacity();//getting capacity of an activity
							passenger_travel_package ptp=null;
							//checking if capacity for an activity is full or not
							if(newActivityCapacity<originalActivityCapacity) {								
								Optional<passenger> t = passDAO.findById(passenger_Number);
								passenger u = t.get();
								int newBalance = u.getNew_balance();//getting remaining balance of the passenger
								double activityCost = q.getCost();
								//10% discount for gold passengers
								if(passengerCategory.equals("Gold")) {
									activityCost = activityCost - (0.1*activityCost);									
								}
								//for gold and standard passengers
								if(!passengerCategory.equals("Premium")) {
									if(newBalance>=activityCost) {
										actDAO.update(dest);//increasing activity filled capacity
										ptp = new passenger_travel_package(packageName,passengerName,passenger_Number,a,dest);
										passDAO.update(activityCost, passenger_Number);//updating remaining balance of passenger
									}
								}
								else {//for premium passengers 0 cost
									actDAO.update(dest);
									ptp = new passenger_travel_package(packageName,passengerName,passenger_Number,a,dest);
								}								
							}							
							if(ptp!=null)
								passTravelPackageDAO.save(ptp);
						}					
					}
				}
			}
			travPackageDAO.update(packageName);//updating passenger capacity of package
			return 1;
		}
		else {
			return 0;
		}		
	}
	//returning entire table travel_package
	public List<travel_package> getTravelPackage(){
		return travPackageDAO.findAll();
	}
	//returning details of activity of a particular destination
	public activity getActivityItinerary(String dest) {
		Optional<activity> x= actDAO.findById(dest);
		return x.get();
	}
	//returning total number of passengers in a package
	public long getPassengerCount(String packName) {
		Optional<travel_package> x = travPackageDAO.findById(packName);
		travel_package t = x.get();
		return t.getNew_capacity();
	}
	
	//returning list of all passenger name and number for a package
	public List<Object[]> getPassengerList(String packName){
		return passTravelPackageDAO.findPassengersByPackage(packName);
	}
	
	//returning list of all passengers
	public List<passenger> getPassList(){
		return passDAO.findAll();
	}
	
	//returning all activities selected by a passenger
	public List<String> getActivities(int passNumber) {
		return passTravelPackageDAO.getActivities(passNumber);
	}
	
	//returning all destinations where passenger will go
	public List<String> getDestination(int num) {
		return passTravelPackageDAO.getDestination(num);
	}
	
	//returning activity at a destination
	public String getActivityFromDestination(String dest) {
		return destDAO.getDest(dest);
	}
	
	//returning cost of activity at a destination
	public int getActivityCost(String dest) {
		return actDAO.getCost(dest);
	}
	
	//returning all destinations where activity capacity is not full
	public List<String> getRemainingDestinations(){
		return actDAO.getDestination();
	}
	
	//returning details of activity at a destination
	public activity activityDetails(String dest) {
		Optional<activity> x = actDAO.findById(dest);
		return x.get();
	}
	
	
}

