
package IoT.org.repository;

import IoT.org.entity.Reading;
import IoT.org.entity.VehicleInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReadingRepository extends CrudRepository<Reading,String> {

@Query(value = " select  * from reading  where vin = :vinid ", nativeQuery = true)
    List<Reading> findGeoLocation(@Param("vinid") String vin);

}
