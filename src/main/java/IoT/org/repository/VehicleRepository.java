package IoT.org.repository;

import IoT.org.entity.VehicleInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends CrudRepository<VehicleInfo, String>
{


}
