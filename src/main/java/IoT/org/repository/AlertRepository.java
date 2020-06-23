package IoT.org.repository;

import IoT.org.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<Alert,String> {

    @Query(value = "SELECT * FROM alert where priority = 'High'  ",nativeQuery = true)
    List<Alert> getHighAlerts();


}
