package travel.TravelAgency.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import travel.TravelAgency.Model.activity;


@Repository
public interface activityDAO extends JpaRepository<activity, String> {
	//incrementing new_activity(filled capacity) for a particular destination by 1 in DB
	@Transactional
    @Modifying
	@Query(value="update activity set new_capacity=new_capacity+1 where destination_name=:name",nativeQuery=true)
	void update(@Param("name")String name);
	
	//retrieving new_capacity(filled capacity) for a particular destination and activity
	@Query(value="select new_capacity from activity where destination_name=%?1% and name=%?2%",nativeQuery=true)
	int getNewCapacity(String dest, String actName);
	
	//checking if a particular destination offers that activity or not, since we are following 1 destination 1 activity
	//it will return 1 if condition is satisfied else 0
	@Query(value="SELECT COALESCE((SELECT CASE WHEN destination_name = %?1% AND name = %?2% THEN 1 ELSE 0 END FROM activity WHERE destination_name = %?1% AND name = %?2%), 0) AS result",nativeQuery=true)
	int checkCombination(String dest, String actName);
	
	//retrieving cost of activity from a particular destination
	@Query(value="select cost from activity where destination_name=:dest")
	int getCost(@Param("dest")String destination);
	
	//retrieving all destinations where activity capacity is remaining
	@Query(value="select destination_name from activity where (capacity-new_capacity)!=0")
	List<String> getDestination();
}
