package travel.TravelAgency.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import travel.TravelAgency.Model.passenger;


@Repository
public interface passengerDAO extends JpaRepository<passenger, Integer> {
	//storing the passenger info in DB
	@Transactional
    @Modifying
	@Query(value="update passenger set new_balance=new_balance-:cost where passenger_number=:number",nativeQuery=true)
	void update(@Param("cost") double c,@Param("number") int n);

}
