package travel.TravelAgency.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import travel.TravelAgency.Model.passenger_travel_package;


@Repository
public interface passenger_travel_packageDAO extends JpaRepository<passenger_travel_package, Integer> {
	//retrieving list of activities a passenger has selected
	@Query(value="select activity_name from passenger_travel_package where passenger_number=:num")
	List<String> getActivities(@Param("num") int number);
	
	//retrieving list of destinations for a particular passenger who wants to go to a location and do an activity
	@Query(value="select destination_name from passenger_travel_package where passenger_number=:num")
	List<String> getDestination(@Param("num") int number);
	
	//retrieving list of passengers for a particular package
	@Query(value="SELECT DISTINCT passenger_name, passenger_number FROM passenger_travel_package WHERE package_name=:packageName",nativeQuery=true)
    List<Object[]> findPassengersByPackage(@Param("packageName")String pName);

}
