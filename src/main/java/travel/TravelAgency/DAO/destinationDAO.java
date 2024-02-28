package travel.TravelAgency.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import travel.TravelAgency.Model.destination;


@Repository
public interface destinationDAO extends JpaRepository<destination, String> {
	//retrieving name of activity for a particular destination 
	@Query(value="select activity_name from destination where name=:a")
	String getDest(@Param("a") String destName);

}
