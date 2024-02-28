package travel.TravelAgency.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import travel.TravelAgency.Model.travel_package;


@Repository
public interface travel_packageDAO extends JpaRepository<travel_package, String> {
	//incrementing filled capacity of a particular package by 1 which was selected by passenger 
	@Transactional
    @Modifying
	@Query(value="update travel_package set new_capacity=new_capacity+1 where name=:name",nativeQuery=true)
	void update(@Param("name")String name);
}
