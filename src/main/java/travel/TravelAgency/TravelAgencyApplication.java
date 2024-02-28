package travel.TravelAgency;


import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import travel.TravelAgency.Model.activity;
import travel.TravelAgency.Model.passenger;
import travel.TravelAgency.Model.travel_package;
import travel.TravelAgency.Service.travelService;

@SpringBootApplication
public class TravelAgencyApplication implements CommandLineRunner {
	@Autowired
	private travelService tService;

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<travel_package> t = tService.getTravelPackage();
		
		List<String> packageName= new ArrayList<>();
		for(travel_package tPackage:t) {
			packageName.add(tPackage.getName());
		}		
		
		System.out.println("Enter 1 for adding Passengers and 2 for viewing the results");
		int decision = sc.nextInt();
		
		if(decision==1) {
			while(true) {			
				String packName=null;
				String[] destArr;
				List<String> l = new ArrayList<>();
				System.out.println("Enter Passenger name, Number, Category (Standard,Gold,Premium), Balance");
				String pName=sc.next();
				int pNum=sc.nextInt();
				String pCat=sc.next();
				int pBal=sc.nextInt();
				System.out.println("Select package from this package List");		
				for(travel_package tPack:t) {
					System.out.print(tPack.getName()+" ");
					System.out.println("If you want to select this enter 1 else enter 0");
					int s=sc.nextInt();
					if(s==1) {
						packName=tPack.getName();
						String itinerary = tPack.getItinerary();
						destArr = itinerary.split("-");
						for(String d:destArr) {
							activity actDet = tService.activityDetails(d);
							System.out.println("Activity "+actDet.getName());
							System.out.println("Enter 1 for selecting this activity and 0 for not selecting");
							int select = sc.nextInt();
							if(select==1)
								l.add(actDet.getName());
							else
								l.add(null);
						}				
						break;
					}
				}
				int result = tService.addPassenger(pName, pNum, pCat, pBal, packName, l);
				if(result==1)
					System.out.println("Passenger Added");
				else
					System.out.println("Passenger Capacity Exceeded");
				System.out.println("If you want to add more passengers enter 1 else 0");
				int add=sc.nextInt();
				if(add==0)
					break;
			}
		}
		else if(decision==2) {
			//1				
			for(travel_package tP:t) {
				System.out.println("Package Name : "+tP.getName());
				String itinerary = tP.getItinerary();
				String[] destArray = itinerary.split("-");
				for(String dest:destArray) {
					activity act = tService.getActivityItinerary(dest);
					System.out.println(act.toString());
					System.out.println();
				}
				System.out.println();
			}
			System.out.println();
			
			//2
			for(travel_package tP:t) {
				System.out.println("Package Name : "+tP.getName());
				System.out.println("Passenger Capacity : "+tP.getPassenger_capacity());
				System.out.println("Number of Passengers Enrolled : "+tService.getPassengerCount(tP.getName()));
				List<Object[]> result = tService.getPassengerList(tP.getName());
				for (Object[] row : result) {
				    String passengerName = (String) row[0];
				    Integer passengerNumber = (Integer) row[1];				    
				    System.out.println("Passenger Name: " + passengerName + ", Passenger Number: " + passengerNumber);
				}
				System.out.println();
			}
			System.out.println();
			
			//3
			List<passenger> pass = tService.getPassList();
			for(passenger p:pass) {
				System.out.print("Name : "+p.getName()+"  ");
				System.out.print("Passenger Number : "+p.getPassenger_number()+"  ");
				System.out.print("Balance : "+p.getBalance()+"  ");
				List<String> activities = tService.getActivities(p.getPassenger_number());
				System.out.print("Activities : ");
				if(activities!=null)
				{					 
						List<String> pDest=tService.getDestination(p.getPassenger_number());
						for(String d:pDest) {
							String actDest = tService.getActivityFromDestination(d);
							System.out.print(actDest+" - "+d+"  ");
							int cost = tService.getActivityCost(d);
							if(p.getCategory().equals("Gold")) 
								cost = cost-(int)((10.0/100)*cost);
							else if(p.getCategory().equals("Premium"))
								cost=0;					
							System.out.println("Price paid : "+cost+" , ");
						}					
				}				
				else
					System.out.print("None");
				
				System.out.println();
			}
			System.out.println();
			
			//4
			List<String> remainingActivityDest = tService.getRemainingDestinations();
			if(remainingActivityDest!=null) {
				for(String dest:remainingActivityDest) {
					activity actDetails = tService.activityDetails(dest);				
					int space = actDetails.getCapacity()-actDetails.getNew_capacity();
					System.out.println(actDetails.toString()+" Space Left "+space);
				}			
			}
			else
				System.out.println("No spaces available in any Activity");
		}
		sc.close();
	}
}
